package com.dhc.home

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val isShowBottomSheet: Boolean = false,
    ): UiState


    sealed interface Event: UiEvent {
        data object ClickMissionComplete: Event
        data object ClickFinishMissionButton: Event
        data object DismissMissionComplete: Event

    }

    sealed interface SideEffect : UiSideEffect {
        data class ShowToast(val msg: String): SideEffect

    }
}
