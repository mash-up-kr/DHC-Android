package com.dhc.home.main

import com.dhc.home.main.HomeContract.Event
import com.dhc.home.main.HomeContract.State
import com.dhc.home.main.HomeContract.SideEffect
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickMoreButton, Event.ClickFortuneCard -> postSideEffect(SideEffect.NavigateToMonetaryDetailScreen)
        }
    }

}