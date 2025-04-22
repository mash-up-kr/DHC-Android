package com.dhc.dhcandroid.home

import com.dhc.dhcandroid.mvi.UiEvent
import com.dhc.dhcandroid.mvi.UiSideEffect
import com.dhc.dhcandroid.mvi.UiState


class HomeContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class HomeState(
        val isLoading: Boolean = false,
        val number: Int = 0,
    ): UiState


    /**
     * 액션 정의
     */
    sealed interface HomeEvent: UiEvent {
        data object ClickAddButton : HomeEvent
        data object ClickMinusButton : HomeEvent
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface HomeSideEffect : UiSideEffect {
        data class ShowToast(val msg: String): HomeSideEffect

    }
}