package com.dhc.reward.yearfortune

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class YearFortuneContract {

    data class State(
        val yearFortuneState: YearFortuneState = YearFortuneState.Loading,
        val yearFortuneInfo: YearFortuneInfo = YearFortuneInfo(),
    ) : UiState

    sealed interface YearFortuneState {
        data object Loading : YearFortuneState
        data object Success : YearFortuneState
        data object Error : YearFortuneState
    }

    sealed interface Event : UiEvent {
        data object ClickErrorRetryButton : Event
        data object ClickBackButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect
    }
}
