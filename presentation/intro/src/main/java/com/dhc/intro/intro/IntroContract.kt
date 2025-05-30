package com.dhc.intro.intro

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroContract {

    data class State(
        val page: Int = 0,
        val totalPage: Int = 1,
    ): UiState {
        val isPageFinished = page == totalPage
    }

    sealed interface Event: UiEvent {
        data object ClickNextButton : Event
    }

    sealed interface SideEffect : UiSideEffect
}
