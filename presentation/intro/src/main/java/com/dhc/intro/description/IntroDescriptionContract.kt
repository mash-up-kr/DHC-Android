package com.dhc.intro.description

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroDescriptionContract {

    class State : UiState

    sealed interface Event : UiEvent {
        data object ClickNextButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
