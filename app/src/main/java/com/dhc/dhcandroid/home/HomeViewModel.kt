package com.dhc.dhcandroid.home

import com.dhc.dhcandroid.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
): BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeSideEffect>() {

    override fun createInitialState(): HomeContract.HomeState {
        return HomeContract.HomeState()
    }

    override suspend fun handleEvent(event: HomeContract.HomeEvent) {
        when(event) {
            is HomeContract.HomeEvent.ClickAddButton -> {
                updateNumber(operator = +1, toastMsg = "add")
            }
            is HomeContract.HomeEvent.ClickMinusButton -> {
                updateNumber(operator = -1, toastMsg = "minus")
            }
        }
    }

    private fun updateNumber(operator: Int, toastMsg: String) {
        reduce { copy(number = state.value.number + operator) }
        postSideEffect(HomeContract.HomeSideEffect.ShowToast(toastMsg))
    }
}