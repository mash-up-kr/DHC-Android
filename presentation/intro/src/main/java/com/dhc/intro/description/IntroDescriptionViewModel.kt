package com.dhc.intro.description

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.description.IntroDescriptionContract.State
import com.dhc.intro.description.IntroDescriptionContract.Event
import com.dhc.intro.description.IntroDescriptionContract.SideEffect

@HiltViewModel
class IntroDescriptionViewModel @Inject constructor(
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
