package com.dhc.missionstatus

import com.dhc.missionstatus.ui.ConsumptionAnalysisUiModel
import com.dhc.missionstatus.ui.MissionAnalysisUiModel
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState
import java.time.LocalDate

class MissionStatusContract {

    data class State(
        val consumptionAnalysisUiModel: ConsumptionAnalysisUiModel? = null,
        val missionAnalysisUiModel: MissionAnalysisUiModel? = null,
    ): UiState


    sealed interface Event: UiEvent {
        data class ClickCalendarDate(val date: LocalDate): Event
    }

    sealed interface SideEffect : UiSideEffect
}
