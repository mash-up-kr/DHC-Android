package com.dhc.mypage

import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.mypage.model.MyInfoUiModel
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class MyPageContract {

    data class State(
        val myInfo: MyInfoUiModel = MyInfoUiModel(),
        val missionCategories: List<MissionCategory> = emptyList(),
    ) : UiState

    sealed interface Event : UiEvent

    sealed interface SideEffect : UiSideEffect
}
