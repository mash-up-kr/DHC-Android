package com.dhc.intro.fortunecard

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.fortunecard.IntroFortuneCardContract.State
import com.dhc.intro.fortunecard.IntroFortuneCardContract.Event
import com.dhc.intro.fortunecard.IntroFortuneCardContract.SideEffect

@HiltViewModel
class IntroFortuneCardViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickNextButton -> postSideEffect(SideEffect.NavigateToNextScreen)
            Event.FlippedFortuneCard -> reduce { copy(isCardFlipped = true) }
        }
    }
}
