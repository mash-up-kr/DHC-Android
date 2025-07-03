package com.dhc.home.detail

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckInfo

class MoneyDetailLuckContract {

    data class State(
        val monetaryLuckInfo: MonetaryLuckInfo = MonetaryLuckInfo()
    ): UiState


    sealed interface Event: UiEvent {
        data object ClickConfirm: Event

    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToMissionConfirm: SideEffect
    }
}
