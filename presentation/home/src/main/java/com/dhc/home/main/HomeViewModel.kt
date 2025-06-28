package com.dhc.home.main

import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.State
import com.dhc.home.main.HomeContract.SideEffect
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
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionCompleteBottomSheet = isShowBottomSheet) }
    }

    private fun updateMissionSuccessDialogState(isShowDialog: Boolean) {
        reduce { copy(isShowMissionSuccessDialog = isShowDialog) }
    }
}