package com.dhc.home

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val number: Int = 0,
    ): UiState


    sealed interface Event: UiEvent {
        data object ClickAddButton : Event
        data object ClickMinusButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data class ShowToast(val msg: String): SideEffect

    }
}
