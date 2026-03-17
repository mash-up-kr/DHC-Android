package com.example.survey

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class SurveyContract {

    data class State(
        val shareToken: String? = null,
        val isLoading: Boolean = true,
        val landingUrl: String = "",
    ) : UiState

    sealed interface Event : UiEvent

    sealed interface SideEffect : UiSideEffect
}
