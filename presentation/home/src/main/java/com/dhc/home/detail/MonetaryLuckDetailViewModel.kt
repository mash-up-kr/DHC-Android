package com.dhc.home.detail

import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject
import com.dhc.home.detail.MoneyDetailLuckContract.State
import com.dhc.home.detail.MoneyDetailLuckContract.Event
import com.dhc.home.detail.MoneyDetailLuckContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MonetaryLuckDetailViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
    }

}