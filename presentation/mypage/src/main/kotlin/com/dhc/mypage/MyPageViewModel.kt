package com.dhc.mypage

import com.dhc.mypage.MyPageContract.State
import com.dhc.mypage.MyPageContract.Event
import com.dhc.mypage.MyPageContract.SideEffect
import com.dhc.presentation.mvi.BaseViewModel
import javax.inject.Inject

class MyPageViewModel @Inject constructor(
): BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        TODO("Not yet implemented")
    }
}
