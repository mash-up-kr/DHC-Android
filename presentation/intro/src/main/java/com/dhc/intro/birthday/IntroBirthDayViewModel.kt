package com.dhc.intro.birthday

import com.dhc.dhcandroid.model.BirthDate
import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.birthday.IntroBirthDayContract.State
import com.dhc.intro.birthday.IntroBirthDayContract.Event
import com.dhc.intro.birthday.IntroBirthDayContract.SideEffect

@HiltViewModel
class IntroBirthDayViewModel @Inject constructor(
    private val repository: UserRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> {
                repository.updateBirthDay(
                    BirthDate(
                        date = event.currentState.date,
                        calendarType = event.currentState.calendarType.toServerType(),
                    )
                )
                postSideEffect(SideEffect.NavigateToNextScreen)
            }
            is Event.SelectBirthDay -> reduce { copy(year = event.year, month = event.month, day = event.day) }
            is Event.SelectCalendarType -> reduce { copy(calendarType = event.selectedValue) }
        }
    }
}
