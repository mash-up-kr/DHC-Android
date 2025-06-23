package com.dhc.missionstatus

import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.MissionStatusContract.Event
import com.dhc.missionstatus.MissionStatusContract.SideEffect
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class MissionStatusViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State() // Todo - 변경 필요
    }

    override suspend fun handleEvent(event: Event) {
        // Todo - 구현 필요
    }
}
