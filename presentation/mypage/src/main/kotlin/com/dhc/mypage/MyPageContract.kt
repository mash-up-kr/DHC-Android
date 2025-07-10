package com.dhc.mypage

import com.dhc.mypage.model.MissionCategoryUiModel
import com.dhc.mypage.model.MyInfoUiModel
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class MyPageContract {

    data class State(
        val myInfo: MyInfoUiModel = MyInfoUiModel(),
        val missionCategories: List<MissionCategoryUiModel> = emptyList(),
        val isShowAppResetDialog: Boolean = false,
        val userId: String = ""
    ) : UiState

    sealed interface Event : UiEvent {
        data object ClickAppResetButton : Event
        data object ClickAppResetConfirmButton : Event
        data object ClickDialogDismissButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToIntro : SideEffect
        data class ShowToast(val message: String) : SideEffect
    }
}
