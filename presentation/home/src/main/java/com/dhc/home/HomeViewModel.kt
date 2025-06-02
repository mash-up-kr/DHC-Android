package com.dhc.home

import com.dhc.home.HomeContract.Event
import com.dhc.home.HomeContract.State
import com.dhc.home.HomeContract.SideEffect
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        TODO("Not yet implemented")
    }

    override suspend fun handleEvent(event: Event) {
        TODO("Not yet implemented")
    }

}