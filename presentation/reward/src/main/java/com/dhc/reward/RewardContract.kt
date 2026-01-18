package com.dhc.reward

import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState
import com.dhc.reward.model.RewardUiModel

class RewardContract {

    data class State(
        val rewardState: RewardState = RewardState.Loading,
        val rewardInfo: RewardUiModel = RewardUiModel(),
    ) : UiState

    sealed interface RewardState {
        data object Loading : RewardState
        data object Success : RewardState
        data object Error : RewardState
    }

    sealed interface Event : UiEvent {
        data object ClickOpenRewardButton : Event
        data object ClickErrorRetryButton : Event
        data class ClickRewardItem(val itemId: Int) : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToYearFortune : SideEffect
        data class ShowToast(val msg: String) : SideEffect
    }
}