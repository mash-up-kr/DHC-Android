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
        val date = "$year-$month-$day" // Todo : 어떤식으로 보내는지 확인 후 수정
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
