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
    ) : UiState {
        private val dateFormat = "%04d-%02d-%02d" // Todo : 공통 Formatter 로 이동
        val date: String = dateFormat.format(year, month, day)
    }

    sealed interface Event : UiEvent {
        data class ClickNextButton(val currentState: State) : Event
        data class SelectCalendarType(val selectedValue: CalendarType) : Event
        data class SelectBirthDay(val year: Int, val month: Int, val day: Int) : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
