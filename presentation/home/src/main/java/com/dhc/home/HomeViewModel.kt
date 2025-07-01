package com.dhc.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dhc.common.onException
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.SideEffect
import com.dhc.home.main.HomeContract.State
import com.dhc.home.model.FinishMissionToast
import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionStatusType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.home.model.MissionUiModel
import com.dhc.home.model.SelectChangeMission
import com.dhc.home.model.toToggleMissionRequest
import com.dhc.home.model.toUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    init {
        getHomeInfo()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickMoreButton, Event.ClickFortuneCard -> postSideEffect(
                SideEffect.NavigateToMonetaryDetailScreen)
            is Event.ClickMissionComplete -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = true)
            }
            is Event.ClickMissionCompleteConfirm -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = false)
                if(event.buttonType == MissionCompleteButtonType.Complete)
                    updateMissionSuccessDialogState(isShowDialog = true)
            }
            is Event.ClickMissionSuccess -> {
                updateMissionSuccessDialogState(isShowDialog = false)
                if(event.buttonType == MissionSuccessButtonType.StaticConfirm)
                    postSideEffect(SideEffect.NavigateToMission)
            }
            is Event.ClickMissionChange -> {
                updateSelectedMissionInfo(event.selectChangeMission)
                if(event.selectChangeMission.switchCount >= 4) {
                    updateFinishMissionChangeBottomSheetState(true)
                } else {
                    updateMissionChangeConfirmBottomSheetState(true)
                }
            }
            is Event.ClickMissionChangeConfirm -> {
                updateMissionChangeConfirmBottomSheetState(false)
                rollBackAllCards()
                if(event.buttonType == MissionChangeButtonType.CHANGE) {
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
                updateMissionCardExpanded(missionId = event.missionId, isExpanded = event.isExpanded)
            }
            is Event.ClickMissionCheck -> {
                updateMissionStatus(
                    missionId = event.missionId,
                    missionStatusType = if(event.isChecked) MissionStatusType.COMPLETE else MissionStatusType.INCOMPLETE
                )
            }
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionCompleteBottomSheet = isShowBottomSheet) }
    }

    private fun updateMissionSuccessDialogState(isShowDialog: Boolean) {
        reduce { copy(isShowMissionSuccessDialog = isShowDialog) }
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


    fun getHomeInfo() {
        viewModelScope.launch {
            val userId = userRepository.getUUID()?.firstOrNull() ?: "" //TODO - 추후 변경
            dhcRepository.getHomeView(userId = "685faf11de38af6c7bd9d25d") //TODO - 추후 변경
                .onSuccess { response ->
                    response ?: return@onSuccess
                    reduce { copy(homeInfo = HomeUiModel.from(response) )}
                }.onFailure { _, _ ->
                    // Todo :: 실패 처리
                }
        }
    }

    private fun updateMissionStatus(
        missionId: String,
        missionStatusType: MissionStatusType,
    ) {
        viewModelScope.launch {
            val userId = userRepository.getUUID()?.firstOrNull() ?: "" //TODO - 추후 변경
            dhcRepository.changeMissionStatus(
                userId = "685faf11de38af6c7bd9d25d", //TODO - 추후 변경
                missionId = missionId,
                toggleMissionRequest = missionStatusType.toToggleMissionRequest()
            ).onSuccess { response ->
                response ?: return@onSuccess
                when(missionStatusType) {
                    MissionStatusType.COMPLETE, MissionStatusType.INCOMPLETE  -> { updateMissionCompleteState(missionId, response.missions , missionStatusType) }
                    MissionStatusType.CHANGE -> updateNewMissionList(response.missions, state.value.getMissionIdList())
                }
            }.onFailure { code, message ->
                Log.d("updateMissionStatus", "onFailure:${code} message:${message} ");
            }.onException { e->
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
        if(mission == null) return
        reduce {
            copy(homeInfo = state.value.homeInfo.copy(
                longTermMission = if (state.value.homeInfo.longTermMission.missionId == mission.missionId) {
                    state.value.homeInfo.longTermMission.copy(isChecked = mission.finished.not())
                } else {
                    state.value.homeInfo.longTermMission
                },
                todayDailyMissionList = state.value.homeInfo.todayDailyMissionList.map {
                    if (it.missionId == mission.missionId) {
                        it.copy(isChecked = mission.finished.not())
                    } else {
                        it
                    }
                }
            ))
        }
        if(missionStatusType == MissionStatusType.COMPLETE) {
            postSideEffect(SideEffect.ShowToast(FinishMissionToast.getRandomMessage()))
        }
    }

    private fun updateNewMissionList(missionList: List<Mission>, existIdList: List<String>) {
        val longTermMission = missionList.filter { it.type == MissionType.LONG_TERM }
        val todayDailyMissionList = missionList.filter { it.type == MissionType.DAILY }
        val missionIdList = missionList.map { it.missionId }
        val newIds = missionIdList.firstOrNull { it !in existIdList }
        reduce { copy(homeInfo = state.value.homeInfo.copy(
            longTermMission = longTermMission.firstOrNull()?.toUiModel() ?: MissionUiModel(),
            todayDailyMissionList =  todayDailyMissionList.map { it.toUiModel()},
        ))}
        newIds?.let { updateMissionBlinkState(newIds, true) }
    }

    private fun updateMissionBlinkState(missionId: String, isBlink: Boolean) {
        reduce {
            copy(homeInfo = state.value.homeInfo.copy(
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
            copy(homeInfo = state.value.homeInfo.copy(
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
        reduce { copy(homeInfo = state.value.homeInfo.copy(
            longTermMission = state.value.homeInfo.longTermMission.copy(isExpanded = false),
            todayDailyMissionList = state.value.homeInfo.todayDailyMissionList.map { it.copy(isExpanded = false) }))
        }
    }
}