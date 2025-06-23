package com.dhc.home

import com.dhc.home.HomeContract.Event
import com.dhc.home.HomeContract.State
import com.dhc.home.HomeContract.SideEffect
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State = State()

    override suspend fun handleEvent(event: Event) {
        when(event) {
            is Event.ClickMissionComplete -> {
                updateMissionCompleteBottomSheetState(isShow = true)
            }
            is Event.ClickFinishMissionButton -> {
                updateMissionCompleteBottomSheetState(isShow = false)
            }
            is Event.DismissMissionComplete -> {
                updateMissionCompleteBottomSheetState(isShow = false)
            }
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShow: Boolean) {
        reduce { copy(isShowBottomSheet = isShow) }
    }
}