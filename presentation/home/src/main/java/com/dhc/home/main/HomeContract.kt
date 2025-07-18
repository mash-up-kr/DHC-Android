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
        val homeState: HomeState = HomeState.Loading,
        val isShowMissionCompleteBottomSheet: Boolean = false,
        val isShowMissionSuccessDialog: Boolean = false,
        val isShowMissionChangeBottomSheet: Boolean = false,
        val isShowFinishMissionChangeBottomSheet: Boolean = false,
        val selectedMissionInfo: SelectChangeMission = SelectChangeMission(),
        val homeInfo: HomeUiModel = HomeUiModel(),
        val todaySavedMoney: String = "",
    ): UiState {
        val remainingMissionCount: Int
            get() = getMissionIdList().size - getFinishedMissionCount()

        fun getMissionIdList(): List<String> {
            return listOf(homeInfo.longTermMission.missionId) + homeInfo.todayDailyMissionList.map { it.missionId }
        }

        fun getFinishedMissionCount(): Int {
            val longTermFinished = if (homeInfo.longTermMission.isChecked) 1 else 0
            val dailyFinishedCount = homeInfo.todayDailyMissionList.count { it.isChecked }
            return longTermFinished + dailyFinishedCount
        }
    }

    sealed interface HomeState {
        data object Loading : HomeState
        data object FlipCard : HomeState
        data object Success : HomeState
        data object Error : HomeState
    }

    sealed interface Event : UiEvent {
        data object ClickMoreButton : Event
        data object ClickFortuneCard: Event
        data object ClickTodayMissionFinish: Event
        data class ClickMissionCompleteConfirm(val buttonType: MissionCompleteButtonType): Event
        data class ClickMissionSuccess(val buttonType: MissionSuccessButtonType): Event
        data class ClickMissionChange(val selectChangeMission: SelectChangeMission): Event
        data class ClickMissionChangeConfirm(val buttonType: MissionChangeButtonType): Event
        data object ClickFinishMissionChangeConfirm: Event
        data class BlinkEnd(val missionId: String): Event
        data class ChangeExpandCard(val missionId: String, val isExpanded: Boolean): Event
        data class ClickMissionCheck(val isChecked: Boolean, val missionId: String): Event
        data object FortuneCardFlipped: Event
        data object ClickErrorRetryButton: Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToMonetaryDetailScreen: SideEffect
        data class ShowToast(val msg: String): SideEffect
        data object NavigateToMission: SideEffect
    }
}
