package com.dhc.intro.mission

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.mission.IntroMissionContract.State
import com.dhc.intro.mission.IntroMissionContract.Event
import com.dhc.intro.mission.IntroMissionContract.SideEffect

@HiltViewModel
class IntroMissionViewModel @Inject constructor(
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
