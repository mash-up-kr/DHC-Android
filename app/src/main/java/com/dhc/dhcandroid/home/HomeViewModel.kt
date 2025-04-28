package com.dhc.dhcandroid.home

import com.dhc.dhcandroid.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.dhcandroid.home.HomeContract.State
import com.dhc.dhcandroid.home.HomeContract.Event
import com.dhc.dhcandroid.home.HomeContract.SideEffect

@HiltViewModel
class HomeViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when(event) {
            is Event.ClickAddButton -> {
                updateNumber(operator = +1, toastMsg = "add")
            }
            is Event.ClickMinusButton -> {
                updateNumber(operator = -1, toastMsg = "minus")
            }
        }
    }

    private fun updateNumber(operator: Int, toastMsg: String) {
        reduce { copy(number = state.value.number + operator) }
        postSideEffect(SideEffect.ShowToast(toastMsg))
    }
}