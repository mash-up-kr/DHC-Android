package com.dhc.home.main

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
    ): UiState


    sealed interface Event : UiEvent {
        data object ClickMoreButton : Event
        data object ClickFortuneCard: Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToMonetaryDetailScreen: SideEffect
    }
}
