package com.dhc.intro.start

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.start.IntroStartContract.State
import com.dhc.intro.start.IntroStartContract.Event
import com.dhc.intro.start.IntroStartContract.SideEffect

@HiltViewModel
class IntroStartViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickNextButton -> postSideEffect(SideEffect.NavigateToNextScreen)
        }
    }
}
