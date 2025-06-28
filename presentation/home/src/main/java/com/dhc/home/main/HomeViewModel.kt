package com.dhc.home.main

import androidx.lifecycle.viewModelScope
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionStatusType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val userRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
): BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {
    override fun createInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override suspend fun handleEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.ClickMoreButton, HomeContract.Event.ClickFortuneCard -> postSideEffect(
                HomeContract.SideEffect.NavigateToMonetaryDetailScreen)
            is HomeContract.Event.ClickMissionComplete -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = true)
            }
            is HomeContract.Event.ClickMissionCompleteConfirm -> {
                updateMissionCompleteBottomSheetState(isShowBottomSheet = false)
                if(event.buttonType == MissionCompleteButtonType.Complete)
                    updateMissionSuccessDialogState(isShowDialog = true)
            }
            is HomeContract.Event.ClickMissionSuccess -> {
                updateMissionSuccessDialogState(isShowDialog = false)
                if(event.buttonType == MissionSuccessButtonType.StaticConfirm)
                    postSideEffect(HomeContract.SideEffect.NavigateToMission)
            }
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionCompleteBottomSheet = isShowBottomSheet) }
    }

    private fun updateMissionSuccessDialogState(isShowDialog: Boolean) {
        reduce { copy(isShowMissionSuccessDialog = isShowDialog) }
    }

    fun updateMissionStatus(
        missionId: String,
        missionStatusType: MissionStatusType,
    ) {
        viewModelScope.launch {
            val userId = userRepository.getUUID().firstOrNull().orEmpty()
            val toggleMissionRequest = when(missionStatusType) {
                MissionStatusType.COMPLETE -> ToggleMissionRequest(finished = true)
                MissionStatusType.INCOMPLETE -> ToggleMissionRequest(finished = false)
                MissionStatusType.CHANGE -> ToggleMissionRequest(finished = true)
            }
            dhcRepository.changeMissionStatus(userId = userId, missionId = missionId, toggleMissionRequest)
        }
    }
}