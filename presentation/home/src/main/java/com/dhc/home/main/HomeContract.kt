package com.dhc.home.main

import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.home.model.SelectChangeMission
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val isShowMissionCompleteBottomSheet: Boolean = false,
        val isShowMissionSuccessDialog: Boolean = false,
        val isShowMissionChangeBottomSheet: Boolean = false,
        val isShowFinishMissionChangeBottomSheet: Boolean = false,
        val selectedMissionInfo: SelectChangeMission = SelectChangeMission(),
        val homeInfo: HomeUiModel = HomeUiModel(),
    ): UiState


    sealed interface Event : UiEvent {
        data object ClickMoreButton : Event
        data object ClickFortuneCard: Event
        data object ClickMissionComplete: Event
        data class ClickMissionCompleteConfirm(val buttonType: MissionCompleteButtonType): Event
        data class ClickMissionSuccess(val buttonType: MissionSuccessButtonType): Event
        data class ClickMissionChange(val selectChangeMission: SelectChangeMission): Event
        data class ClickMissionChangeConfirm(val buttonType: MissionChangeButtonType): Event
        data object ClickFinishMissionChangeConfirm: Event
        data class BlinkEnd(val missionId: String): Event
        data class ChangeExpandCard(val missionId: String, val isExpanded: Boolean): Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToMonetaryDetailScreen: SideEffect
        data class ShowToast(val msg: String): SideEffect
        data object NavigateToMission: SideEffect
    }
}
