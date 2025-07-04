package com.dhc.intro.fortunedetail

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckInfo

class IntroFortuneDetailContract {

    data class State(
        val monetaryLuckInfo: MonetaryLuckInfo = MonetaryLuckInfo()
    ) : UiState

    sealed interface Event : UiEvent {
        data object ClickNextButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
