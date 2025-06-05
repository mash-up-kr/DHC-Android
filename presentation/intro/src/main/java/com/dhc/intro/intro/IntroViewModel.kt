package com.dhc.intro.intro

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.intro.IntroContract.State
import com.dhc.intro.intro.IntroContract.Event
import com.dhc.intro.intro.IntroContract.SideEffect

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
