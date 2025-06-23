package com.dhc.mypage

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class MyPageContract {

    class State : UiState

    sealed interface Event : UiEvent

    sealed interface SideEffect : UiSideEffect
}
