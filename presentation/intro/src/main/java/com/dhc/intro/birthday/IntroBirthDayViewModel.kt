package com.dhc.intro.birthday

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.birthday.IntroBirthDayContract.State
import com.dhc.intro.birthday.IntroBirthDayContract.Event
import com.dhc.intro.birthday.IntroBirthDayContract.SideEffect

@HiltViewModel
class IntroBirthDayViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> postSideEffect(SideEffect.NavigateToNextScreen)
            is Event.ClickBackButton -> postSideEffect(SideEffect.NavigateBackScreen)
            is Event.SelectBirthDay -> reduce { copy(year = event.year, month = event.month, day = event.day) }
            is Event.SelectCalendarType -> reduce { copy(calendarType = event.selectedValue) }
        }
    }
}
