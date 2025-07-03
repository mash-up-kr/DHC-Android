package com.dhc.intro.splash

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class SplashContract {

    data class State(
        val isSplashFinished: Boolean = false,
    ) : UiState

    sealed interface Event : UiEvent {
        data object LottieAnimationFinished : Event
    }

    sealed interface SideEffect : UiSideEffect
}
