package com.dhc.home.main

import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val isShowMissionCompleteBottomSheet: Boolean = false,
        val isShowMissionSuccessDialog: Boolean = false
    ): UiState


    sealed interface Event : UiEvent {
        data object ClickMoreButton : Event
        data object ClickFortuneCard: Event
        data object ClickMissionComplete: Event
        data class ClickMissionCompleteConfirm(val buttonType: MissionCompleteButtonType): Event
        data class ClickMissionSuccess(val buttonType: MissionSuccessButtonType): Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToMonetaryDetailScreen: SideEffect
        data class ShowToast(val msg: String): SideEffect
        data object NavigateToMission: SideEffect

    }
}
