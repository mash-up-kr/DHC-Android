package com.dhc.intro.intro

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroContract {

    /**
     * 현재 화면에 필요한 상태들을 모아둔다.
     */
    data class State(
        val page: Int = 0,
        val totalPage: Int = 1,
    ): UiState {
        val isPageFinished = page == totalPage
    }


    /**
     * 액션 정의
     */
    sealed interface Event: UiEvent {
        data object ClickNextButton : Event
    }

    /**
     * SideEffect로 발생되는 이벤트를 정의
     */
    sealed interface SideEffect : UiSideEffect
}
