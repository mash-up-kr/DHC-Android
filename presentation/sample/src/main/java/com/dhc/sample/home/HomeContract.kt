package com.dhc.dhcandroid.home

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class State(
        val isLoading: Boolean = false,
        val number: Int = 0,
    ): com.dhc.presentation.mvi.UiState


    /**
     * 액션 정의
     */
    sealed interface Event: com.dhc.presentation.mvi.UiEvent {
        data object ClickAddButton : Event
        data object ClickMinusButton : Event
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface SideEffect : com.dhc.presentation.mvi.UiSideEffect {
        data class ShowToast(val msg: String): SideEffect

    }
}
