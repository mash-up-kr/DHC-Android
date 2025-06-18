package com.dhc.intro.birthday

import com.dhc.intro.model.CalendarType
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroBirthDayContract {

    data class State(
        val calendarType: CalendarType = CalendarType.SOLAR,
        val year: Int = 2000,
        val month: Int = 1,
        val day: Int = 1,
    ) : UiState

    sealed interface Event : UiEvent {
        data object ClickNextButton : Event
        data object ClickBackButton : Event
        data class SelectCalendarType(val selectedValue: CalendarType) : Event
        data class SelectBirthDay(val year: Int, val month: Int, val day: Int) : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
        data object NavigateBackScreen: SideEffect
    }
}
