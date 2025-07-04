package com.dhc.intro.birthtime

import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.birthtime.IntroBirthTimeContract.State
import com.dhc.intro.birthtime.IntroBirthTimeContract.Event
import com.dhc.intro.birthtime.IntroBirthTimeContract.SideEffect

@HiltViewModel
class IntroBirthTimeViewModel @Inject constructor(
    private val repository: UserRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> {
                repository.updateBirthTime(event.currentState.birthTime)
                postSideEffect(SideEffect.NavigateToNextScreen)
            }
            is Event.SelectBirthTime -> reduce {
                copy(
                    timeType = event.timeType,
                    time = event.time,
                    minute = event.minute
                )
            }
            is Event.SelectIdkButton -> reduce { copy(isIdkChecked = event.isIdkEnabled) }
        }
    }
}
