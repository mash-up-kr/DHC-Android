package com.dhc.home

import androidx.lifecycle.viewModelScope
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.home.main.HomeContract
import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.home.model.MissionCompleteButtonType
import com.dhc.home.model.MissionStatusType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
): BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {
    override fun createInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    init {
        getHomeInfo()
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
            is HomeContract.Event.ClickMissionChange -> {
                //TODO - 바꾸기 count에 따라 바텀시트 분기
                updateMissionChangeConfirmBottomSheetState(true)
            }
            is HomeContract.Event.ClickMissionChangeConfirm -> {
                updateMissionChangeConfirmBottomSheetState(false)
                if(event.buttonType == MissionChangeButtonType.CHANGE) {
                    //updateMissionStatus(missionId = event.missionId, missionStatusType = MissionStatusType.CHANGE)
                    postSideEffect(HomeContract.SideEffect.ChangeMissionBoarder(missionId = 0))  // TODO - id 변경
                }
            }
            is HomeContract.Event.ClickFinishMissionChangeConfirm -> {
                updateFinishMissionChangeBottomSheetState(false)
            }
        }
    }

    private fun updateMissionCompleteBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionCompleteBottomSheet = isShowBottomSheet) }
    }

    private fun updateMissionSuccessDialogState(isShowDialog: Boolean) {
        reduce { copy(isShowMissionSuccessDialog = isShowDialog) }
    }

    private fun updateMissionChangeConfirmBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowMissionChangeBottomSheet = isShowBottomSheet) }
    }

    private fun updateFinishMissionChangeBottomSheetState(isShowBottomSheet: Boolean) {
        reduce { copy(isShowFinishMissionChangeBottomSheet = isShowBottomSheet) }
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
            dhcRepository.changeMissionStatus(
                userId = userId,
                missionId = missionId,
                toggleMissionRequest = toggleMissionRequest
            ).onSuccess {

            }
        }
    }

    fun getHomeInfo() {
        viewModelScope.launch {
            val userId = userRepository.getUUID().firstOrNull().orEmpty()
            dhcRepository.getHomeView(userId = "68600689fe2fbbba96b0ab4a")
                .onSuccess { response ->
                    response ?: return@onSuccess
                    reduce { copy(homeInfo = HomeUiModel.from(response) )}
                }.onFailure { _, _ ->
                    // Todo :: 실패 처리
                }
        }
    }
}