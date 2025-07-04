package com.dhc.intro.gender

import com.dhc.intro.model.Gender
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroGenderContract {

    data class State(
        val gender: Gender? = null,
    ) : UiState

    sealed interface Event : UiEvent {
        data class SelectGender(val gender: Gender) : Event
        data class ClickNextButton(val currentState: State) : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
