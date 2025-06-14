package com.dhc.intro.start

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.start.IntroContract.State
import com.dhc.intro.start.IntroContract.Event
import com.dhc.intro.start.IntroContract.SideEffect

@HiltViewModel
class IntroViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when(event) {
            Event.ClickNextButton -> {
                reduce { copy(page = state.value.page + 1) }
            }
        }
    }
}
