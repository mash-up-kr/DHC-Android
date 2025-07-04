package com.dhc.intro.fortunecard

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroFortuneCardContract {

    data class State(
        val isCardFlipped: Boolean = false,
    ) : UiState

    sealed interface Event : UiEvent {
        data object FlippedFortuneCard : Event
        data object ClickNextButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
