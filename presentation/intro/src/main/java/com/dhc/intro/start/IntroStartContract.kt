package com.dhc.intro.start

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroStartContract {

    data class State(
        val isPageFinished: Boolean = false,
    ) : UiState

    sealed interface Event : UiEvent {
        data object ClickNextButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
