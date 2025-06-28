package com.dhc.home

import androidx.lifecycle.viewModelScope
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.model.MissionType
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.SideEffect
import com.dhc.home.main.HomeContract.State
import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionStatusType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.home.model.SelectChangeMission
import com.dhc.home.model.getMissionIdList
import com.dhc.home.model.toUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
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
                if(event.selectChangeMission.switchCount > 4) {
                    updateFinishMissionChangeBottomSheetState(true)
                } else {
                    updateMissionChangeConfirmBottomSheetState(true)
                }
            }
            is Event.ClickMissionChangeConfirm -> {
                updateMissionChangeConfirmBottomSheetState(false)
                if(event.buttonType == MissionChangeButtonType.CHANGE) {
                    updateMissionStatus(
                        missionId = state.value.selectedMissionInfo.missionId,
                        missionStatusType = MissionStatusType.CHANGE
                    )
                    postSideEffect(SideEffect.ChangeMissionBoarder(missionId = state.value.selectedMissionInfo.missionId ))
                }
            }
            is Event.ClickFinishMissionChangeConfirm -> {
                updateFinishMissionChangeBottomSheetState(false)
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

    fun updateSelectedMissionInfo(selectedMissionInfo: SelectChangeMission) {
        reduce { copy(selectedMissionInfo = selectedMissionInfo) }
    }

    fun updateMissionStatus(
        missionId: String,
        missionStatusType: MissionStatusType,
    ) {
        viewModelScope.launch {
            val userId = userRepository.getUUID().firstOrNull().orEmpty()
            val existIdList = getMissionIdList(state.value.homeInfo.longTermMission, state.value.homeInfo.todayDailyMissionList)
            val toggleMissionRequest = when(missionStatusType) {
                MissionStatusType.COMPLETE -> ToggleMissionRequest(finished = true)
                MissionStatusType.INCOMPLETE -> ToggleMissionRequest(finished = false)
                MissionStatusType.CHANGE -> ToggleMissionRequest(switch = true)
            }
            dhcRepository.changeMissionStatus(
                userId = "68600689fe2fbbba96b0ab4a", //TODO - 추후 변경
                missionId = missionId,
                toggleMissionRequest = toggleMissionRequest
            ).onSuccess { response ->
                response ?: return@onSuccess
                val longTermMission = response.filter { it.type == MissionType.LONG_TERM }
                val todayDailyMissionList = response.filter { it.type == MissionType.DAILY }
                val missionIdList = response.map { it.missionId }
                val newIds = missionIdList.first { it !in existIdList }
                reduce { copy(homeInfo = state.value.homeInfo.copy(
                    longTermMission = longTermMission.first().toUiModel(),
                    todayDailyMissionList = todayDailyMissionList.toUiModel(),
                ))}
                postSideEffect(SideEffect.ChangeMissionBoarder(newIds))
            }
        }
    }

    fun getHomeInfo() {
        viewModelScope.launch {
            val userId = userRepository.getUUID().firstOrNull().orEmpty()
            dhcRepository.getHomeView(userId = "68600689fe2fbbba96b0ab4a")
                .onSuccess { response ->
                    response ?: return@onSuccess
                    reduce { copy(homeInfo = HomeUiModel.from(response) )}
                }.onFailure { _, _ ->
                    // Todo :: 실패 처리
                }
        }
    }
}