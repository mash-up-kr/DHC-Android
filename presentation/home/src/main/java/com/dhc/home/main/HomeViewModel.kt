package com.dhc.home.main

import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.State
import com.dhc.home.main.HomeContract.SideEffect
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickMoreButton, Event.ClickFortuneCard -> postSideEffect(SideEffect.NavigateToMonetaryDetailScreen)
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
                //TODO - 바꾸기 count에 따라 바텀시트 분기
                updateMissionChangeConfirmBottomSheetState(true)
            }
            is Event.ClickMissionChangeConfirm -> {
                updateMissionChangeConfirmBottomSheetState(false)
                if(event.buttonType == MissionChangeButtonType.CHANGE) {
                    postSideEffect(SideEffect.ChangeMissionBoarder(missionId = 0))  // TODO - id 변경
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
}