package com.dhc.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dhc.common.FormatterUtil.todayStringFormat
import com.dhc.common.onException
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.dhcandroid.repository.FortuneRepository
import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.home.main.HomeContract
import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.SideEffect
import com.dhc.home.main.HomeContract.State
import com.dhc.home.model.FinishMissionToast
import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionFailType
import com.dhc.home.model.MissionStatusType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.home.model.MissionUiModel
import com.dhc.home.model.SelectChangeMission
import com.dhc.home.model.toToggleMissionRequest
import com.dhc.home.model.toUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.dhc.common.DateUtil
import com.dhc.common.DateUtil.formatSecondsToTime
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import java.time.LocalDate
import java.time.ZoneOffset
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val userRepository: UserRepository,
    private val dhcRepository: DhcRepository,
    private val fortuneRepository: FortuneRepository,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    init {
        getHomeInfo()
        checkCompletedLoading()
        getIsFortuneSurveyVisible()
        startMissionTimer()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickMoreButton, Event.ClickFortuneCard -> postSideEffect(
                SideEffect.NavigateToMonetaryDetailScreen
            )

            Event.ClickRewardButton -> postSideEffect(
                SideEffect.NavigateToReward
            )

            is Event.ClickTodayMissionFinish -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = true)
            }

            is Event.ClickMissionCompleteConfirm -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = false)
                if (event.buttonType == MissionCompleteButtonType.Complete) {
                    finishTodayMission()
                }
            }

            is Event.ClickMissionSuccess -> {
                updateMissionSuccessDialogState(isShowDialog = false)
                if (event.buttonType == MissionSuccessButtonType.StaticConfirm)
                    postSideEffect(SideEffect.NavigateToReward)
            }

            is Event.ClickMissionChange -> {
                updateSelectedMissionInfo(event.selectChangeMission)
                if (event.selectChangeMission.switchCount >= 4) {
                    updateFinishMissionChangeBottomSheetState(true)
                } else {
                    updateMissionChangeConfirmBottomSheetState(true)
                }
            }

            is Event.ClickMissionChangeConfirm -> {
                updateMissionChangeConfirmBottomSheetState(false)
                rollBackAllCards()
                if (event.buttonType == MissionChangeButtonType.CHANGE) {
                    updateMissionStatus(
                        missionId = state.value.selectedMissionInfo.missionId,
                        missionStatusType = MissionStatusType.CHANGE
                    )
                }
            }

            is Event.ClickFinishMissionChangeConfirm -> {
                updateFinishMissionChangeBottomSheetState(false)
                rollBackAllCards()
            }

            is Event.BlinkEnd -> {
                updateMissionBlinkState(missionId = event.missionId, isBlink = false)
            }

            is Event.ChangeExpandCard -> {
                updateMissionCardExpanded(
                    missionId = event.missionId,
                    isExpanded = event.isExpanded
                )
            }

            is Event.ClickMissionCheck -> {
                updateMissionStatus(
                    missionId = event.missionId,
                    missionStatusType = if (event.isChecked) MissionStatusType.COMPLETE else MissionStatusType.INCOMPLETE
                )
            }

            is Event.FortuneCardFlipped -> {
                delay(FLIPPED_TO_SUCCESS_DELAY_MS)
                val seenFortuneList = fortuneRepository.getSeenFortuneList().firstOrNull() ?: emptySet()
                val currentLocalDate = LocalDate.now()
                val currentLocalDateEpochSecond = currentLocalDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
                if (seenFortuneList.contains(currentLocalDateEpochSecond).not()) {
                    fortuneRepository.addSeenFortune(currentLocalDateEpochSecond)
                }
                reduce { copy(homeState = HomeContract.HomeState.Success) }
                // 플립 완료 후 Success 전환 시점에 팝업 체크
                showReEntryPopupIfNeeded()
            }

            is Event.ClickErrorRetryButton -> {
                getHomeInfo()
            }

            is Event.ClickFortuneSurveyClose -> {
                reduce { copy(isFortuneSurveyVisible = false) }
                userRepository.updateIsShownFortunePopup(true)
            }

            is Event.ClickFortuneSurveySubmit -> {
                reduce { copy(isFortuneSurveyVisible = false) }
                userRepository.updateIsShownFortunePopup(true)
                postSideEffect(SideEffect.NavigateToFortuneSurvey(event.url))
            }

            is Event.ClickMissionFailConfirmButton -> {
                updateMissionFailDialogState(isShowDialog = false)
            }
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionCompleteBottomSheet = isShowBottomSheet) }
    }

    private fun updateMissionSuccessDialogState(isShowDialog: Boolean) {
        reduce { copy(isShowMissionSuccessDialog = isShowDialog) }
    }

    private fun updateMissionFailDialogState(isShowDialog: Boolean, missionFailType: MissionFailType = MissionFailType.NORMAL) {
        reduce { copy(isShowMissionFailDialog = isShowDialog, missionFailType = missionFailType) }
        if (isShowDialog) {
            updateRewardText(missionFailType)
        }
    }

    private fun updateRewardText(missionFailType: MissionFailType) {
        reduce {
            copy(
                homeInfo = homeInfo.copy(
                    rewardEvent = homeInfo.rewardEvent.copy(
                        rewardEventTitle = missionFailType.mainMessage,
                        rewardEventSubtitle = missionFailType.subMessage
                    )
                )
            )
        }
    }

    private fun determineMissionFailType(): MissionFailType? {
        val homeInfo = state.value.homeInfo
        return when {
            // C: 가입 첫날 → 팝업 스킵
            homeInfo.isFirstAccess -> null
            // B: 2일 이상 미접속
            homeInfo.longAbsence -> MissionFailType.LONG_ABSENCE
            // A: 어제 미션 실패
            !homeInfo.yesterdayMissionSuccess -> MissionFailType.NEXT_DAY_REENTRY
            // A: 어제 미션 성공 → 팝업 없음
            else -> null
        }
    }

    private fun updateMissionChangeConfirmBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionChangeBottomSheet = isShowBottomSheet) }
    }

    private fun updateFinishMissionChangeBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowFinishMissionChangeBottomSheet = isShowBottomSheet) }
    }

    private fun updateSelectedMissionInfo(selectedMissionInfo: SelectChangeMission) {
        reduce { copy(selectedMissionInfo = selectedMissionInfo) }
    }


    private fun getHomeInfo() {
        viewModelScope.launch {
            reduce { copy(homeState = HomeContract.HomeState.Loading) }
            val userIdDeferred = async { authRepository.getUserId().firstOrNull() }
            val seenFortuneListDeferred = async { fortuneRepository.getSeenFortuneList().firstOrNull() }
            val hasSeenLoveMissionDeferred = async { userRepository.getHasSeenLoveMission() }

            val currentLocalDate = LocalDate.now()
            val currentLocalDateEpochSecond = currentLocalDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
            val userId = userIdDeferred.await()
            val seenFortuneList = seenFortuneListDeferred.await() ?: emptySet()
            val hasSeenLoveMission = hasSeenLoveMissionDeferred.await()

            if (userId != null) {
                dhcRepository.getHomeView(userId = userId)
                    .onSuccess { response ->
                        response ?: return@onSuccess

                        // LOVE 미션 상태 관리 (플래그 저장/리셋)
                        handleLoveMissionState(response, hasSeenLoveMission)

                        val newHomeInfo = HomeUiModel.from(response)
                        val isFortuneAlreadySeen = seenFortuneList.contains(currentLocalDateEpochSecond)
                        reduce {
                            copy(
                                homeInfo = newHomeInfo,
                                homeState = if (isFortuneAlreadySeen) {
                                    HomeContract.HomeState.Success
                                } else {
                                    HomeContract.HomeState.FlipCard
                                },
                            )
                        }

                        // LOVE 미션 최초 등장 시 깜빡임 적용
                        applyLoveMissionBlinkIfNeeded(response, hasSeenLoveMission)

                        // 포춘 이미 본 경우: Success 상태이므로 바로 팝업 체크
                        // FlipCard 상태인 경우: FortuneCardFlipped 이벤트에서 처리
                        if (isFortuneAlreadySeen) {
                            viewModelScope.launch { showReEntryPopupIfNeeded() }
                        }
                    }.onFailure { _, _ ->
                        reduce { copy(homeState = HomeContract.HomeState.Error) }
                    }
            } else {
                reduce { copy(homeState = HomeContract.HomeState.Error) }
            }
        }
    }

    private fun updateMissionStatus(
        missionId: String,
        missionStatusType: MissionStatusType,
    ) {
        viewModelScope.launch {
            val userId = authRepository.getUserId().firstOrNull() ?: return@launch
            dhcRepository.changeMissionStatus(
                userId = userId,
                missionId = missionId,
                toggleMissionRequest = missionStatusType.toToggleMissionRequest()
            ).onSuccess { response ->
                response ?: return@onSuccess
                when (missionStatusType) {
                    MissionStatusType.COMPLETE, MissionStatusType.INCOMPLETE -> {
                        updateMissionCompleteState(missionId, response.missions, missionStatusType)
                    }

                    MissionStatusType.CHANGE -> updateNewMissionList(
                        response.missions,
                        state.value.getMissionIdList()
                    )
                }
            }.onFailure { code, message ->
                Log.d("updateMissionStatus", "onFailure:${code} message:${message} ");
            }.onException { e ->
                Log.d("updateMissionStatus", "onException:${e} ");
            }
        }
    }

    private fun updateMissionCompleteState(
        missionId: String,
        missionList: List<Mission>,
        missionStatusType: MissionStatusType
    ) {
        val mission = missionList.firstOrNull { it.missionId == missionId }
        if (mission == null) return

        val updatedLongTermMission = if (state.value.homeInfo.longTermMission.missionId == mission.missionId) {
            state.value.homeInfo.longTermMission.copy(isChecked = mission.finished)
        } else {
            state.value.homeInfo.longTermMission
        }

        val updatedDailyMissionList = state.value.homeInfo.todayDailyMissionList.map {
            if (it.missionId == mission.missionId) {
                it.copy(isChecked = mission.finished)
            } else {
                it
            }
        }

        // rewardCompletedCount 계산
        val finishedCount = (if (updatedLongTermMission.isChecked) 1 else 0) +
            updatedDailyMissionList.count { it.isChecked }

        reduce {
            copy(
                homeInfo = state.value.homeInfo.copy(
                    longTermMission = updatedLongTermMission,
                    todayDailyMissionList = updatedDailyMissionList,
                    rewardEvent = state.value.homeInfo.rewardEvent.copy(
                        rewardCompletedCount = finishedCount
                    )
                )
            )
        }
        if (missionStatusType == MissionStatusType.COMPLETE) {
            postSideEffect(SideEffect.ShowToast(FinishMissionToast.getRandomMessage()))
        }
    }

    private fun updateNewMissionList(missionList: List<Mission>, existIdList: List<String>) {
        val longTermMission = missionList.filter { it.type == MissionType.LONG_TERM }
        val todayDailyMissionList = missionList.filter { it.type == MissionType.DAILY || it.type == MissionType.LOVE }
        val missionIdList = missionList.map { it.missionId }
        val newIds = missionIdList.firstOrNull { it !in existIdList }
        reduce {
            copy(
                homeInfo = state.value.homeInfo.copy(
                    longTermMission = longTermMission.firstOrNull()?.toUiModel()
                        ?: MissionUiModel(),
                    todayDailyMissionList = todayDailyMissionList.map { it.toUiModel() },
                )
            )
        }
        newIds?.let { updateMissionBlinkState(newIds, true) }
    }

    private fun updateMissionBlinkState(missionId: String, isBlink: Boolean) {
        reduce {
            copy(
                homeInfo = state.value.homeInfo.copy(
                    longTermMission = if (state.value.homeInfo.longTermMission.missionId == missionId) {
                        state.value.homeInfo.longTermMission.copy(isBlink = isBlink)
                    } else {
                        state.value.homeInfo.longTermMission
                    },
                    todayDailyMissionList = state.value.homeInfo.todayDailyMissionList.map { mission ->
                        if (mission.missionId == missionId) {
                            mission.copy(isBlink = isBlink)
                        } else {
                            mission
                        }
                    }
                ))
        }
    }

    private fun updateMissionCardExpanded(missionId: String, isExpanded: Boolean) {
        reduce {
            copy(
                homeInfo = state.value.homeInfo.copy(
                    longTermMission = if (state.value.homeInfo.longTermMission.missionId == missionId) {
                        state.value.homeInfo.longTermMission.copy(isExpanded = isExpanded)
                    } else {
                        state.value.homeInfo.longTermMission
                    },
                    todayDailyMissionList = state.value.homeInfo.todayDailyMissionList.map { mission ->
                        if (mission.missionId == missionId) {
                            mission.copy(isExpanded = isExpanded)
                        } else {
                            mission
                        }
                    }
                ))
        }
    }

    private fun rollBackAllCards() {
        reduce {
            copy(
                homeInfo = state.value.homeInfo.copy(
                    longTermMission = state.value.homeInfo.longTermMission.copy(isExpanded = false),
                    todayDailyMissionList = state.value.homeInfo.todayDailyMissionList.map {
                        it.copy(
                            isExpanded = false
                        )
                    })
            )
        }
    }

    private suspend fun showReEntryPopupIfNeeded() {
        val todayEpochDay = LocalDate.now().toEpochDay()
        val lastShownEpochDay = userRepository.getLastShownReEntryPopupEpochDay()
        if (lastShownEpochDay == todayEpochDay) return

        val missionFailType = determineMissionFailType()
        if (missionFailType != null) {
            userRepository.updateLastShownReEntryPopupEpochDay(todayEpochDay)
            updateMissionFailDialogState(isShowDialog = true, missionFailType = missionFailType)
        }
    }

    private fun finishTodayMission() {
        if(state.value.getFinishedMissionCount() == 0 ) {
            reduce { copy(homeInfo = homeInfo.copy(todayDone = true)) }
            updateMissionFailDialogState(isShowDialog = true, missionFailType = MissionFailType.NORMAL)
            return
        }
        viewModelScope.launch {
            val userId = authRepository.getUserId().firstOrNull() ?: return@launch
            dhcRepository.requestFinishTodayMissions(
                userId = userId,
                endTodayMissionRequest = EndTodayMissionRequest(
                    date = todayStringFormat
                )
            ).onSuccess {response ->
                response ?: return@onSuccess
                reduce { copy(todaySavedMoney = response.todaySavedMoney, earnedPoint = response.earnedPoint, homeInfo = state.value.homeInfo.copy(todayDone = true)) }
                updateMissionSuccessDialogState(isShowDialog = true)
                dhcRepository.clearCachedCalendarView()
            }.onFailure { code, message ->
                Log.d("finishTodayMission", "onFailure:${code} message:${message} ");
            }
        }
    }

    private fun checkCompletedLoading() = viewModelScope.launch {
        if (state.value.homeState != HomeContract.HomeState.Loading) return@launch

        val userIdDeferred = async { authRepository.getUserId().firstOrNull() }
        val seenFortuneListDeferred = async { fortuneRepository.getSeenFortuneList().firstOrNull() }

        val currentLocalDate = LocalDate.now()
        val currentLocalDateEpochSecond = currentLocalDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
        val userId = userIdDeferred.await()
        val seenFortuneList = seenFortuneListDeferred.await() ?: emptySet()
        if (userId != null) {
            repeat(POLLING_TRY_TIME) {
                dhcRepository.getDailyFortune(userId, currentLocalDate)
                    .onSuccess {
                        reduce {
                            copy(
                                homeState = if (seenFortuneList.contains(currentLocalDateEpochSecond)) {
                                    HomeContract.HomeState.Success
                                } else {
                                    HomeContract.HomeState.FlipCard
                                },
                            )
                        }
                        return@launch
                    }
                delay(POLLING_INTERVAL_TIME_MS)
            }
        } else {
            reduce { copy(homeState = HomeContract.HomeState.Error) }
        }
    }

    private fun getIsFortuneSurveyVisible() {
        viewModelScope.launch {
            val isShown = userRepository.getIsShownFortunePopup()
            reduce { copy(isFortuneSurveyVisible = isShown.not()) }
        }
    }

    private fun midnightTimerFlow() = flow {
        while (currentCoroutineContext().isActive) {
            val remainingSeconds = DateUtil.getTimeUntilMidnight()
            emit(remainingSeconds)

            if (remainingSeconds <= 0) break
            delay(1_000L)
        }
    }

    private var missionTimerJob: Job? = null

    private fun startMissionTimer() {
        missionTimerJob?.cancel()

        missionTimerJob = midnightTimerFlow()
            .map { it.coerceAtLeast(0) }
            .distinctUntilChanged()
            .onEach { seconds ->
                val fourHoursInSeconds = 4 * 60 * 60
                reduce {
                    copy(
                        missionTimerText = formatSecondsToTime(seconds),
                        isTimerUnderFourHours = seconds in 1..fourHoursInSeconds
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun stopMissionTimer() {
        missionTimerJob?.cancel()
    }

    /**
     * LOVE 미션 상태 관리
     * - LOVE 미션이 있으면 hasSeenLoveMission = true 저장
     * - LOVE 미션이 없으면 hasSeenLoveMission = false로 리셋
     */
    private fun handleLoveMissionState(response: com.dhc.dhcandroid.model.HomeViewResponse, currentHasSeenLoveMission: Boolean) {
        viewModelScope.launch {
            val hasLoveMission = response.todayDailyMissionList.any { it.type == MissionType.LOVE }

            if (hasLoveMission && !currentHasSeenLoveMission) {
                // LOVE 미션 최초 등장 → true 저장
                userRepository.updateHasSeenLoveMission(true)
            } else if (!hasLoveMission && currentHasSeenLoveMission) {
                // LOVE 미션 사라짐 → false로 리셋
                userRepository.updateHasSeenLoveMission(false)
            }
        }
    }

    /**
     * LOVE 미션 최초 등장 시 깜빡임 효과 적용
     */
    private fun applyLoveMissionBlinkIfNeeded(response: com.dhc.dhcandroid.model.HomeViewResponse, hasSeenLoveMission: Boolean) {
        if (hasSeenLoveMission) {
            // 이미 본 적 있으면 깜빡임 없음
            return
        }

        // LOVE 미션 찾기
        val loveMission = response.todayDailyMissionList.firstOrNull { it.type == MissionType.LOVE }
        if (loveMission != null) {
            // 최초 등장 시 깜빡임 적용 (기존 updateMissionBlinkState 재사용)
            updateMissionBlinkState(missionId = loveMission.missionId, isBlink = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopMissionTimer()
    }

    companion object {
        private const val POLLING_TRY_TIME = 5
        private const val POLLING_INTERVAL_TIME_MS = 5000L
        private const val FLIPPED_TO_SUCCESS_DELAY_MS = 1500L
    }
}
