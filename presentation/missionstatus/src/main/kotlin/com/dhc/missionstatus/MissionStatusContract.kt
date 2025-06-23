package com.dhc.missionstatus

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class MissionStatusContract {

    data class State(
        val isLoading: Boolean = false,
    ): UiState


    sealed interface Event: UiEvent

    sealed interface SideEffect : UiSideEffect
}
