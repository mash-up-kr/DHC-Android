package com.dhc.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.home.main.FinishMissionChangeBottomSheet
import com.dhc.home.main.HomeContract
import com.dhc.home.main.HomeScreen
import com.dhc.home.main.MissionChangeBottomSheet
import com.dhc.home.ui.MissionCompleteCheckBottomSheet
import com.dhc.home.ui.MissionSuccessDialog

@Composable
fun HomeRoute(
    navigateToMission: () -> Unit,
    navigateToMonetaryLuckDetail: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var reRollExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is HomeContract.SideEffect.NavigateToMonetaryDetailScreen -> navigateToMonetaryLuckDetail()
                is HomeContract.SideEffect.NavigateToMission -> navigateToMission()
                is HomeContract.SideEffect.ShowToast -> {}
                is HomeContract.SideEffect.ReRollExpanded -> { reRollExpanded = true }
            }
        }
    }

    Box {
        HomeScreen(
            state = state,
            eventHandler = viewModel::sendEvent,
            reRollExpanded = reRollExpanded,
        )
        if(state.isShowMissionCompleteBottomSheet) {
            MissionCompleteCheckBottomSheet(
                missionCount = 0,
                eventHandler = viewModel::sendEvent
            )
        }

        if(state.isShowMissionSuccessDialog) {
            MissionSuccessDialog(
                eventHandler = viewModel::sendEvent
            )
        }

        if(state.isShowMissionChangeBottomSheet) {
            MissionChangeBottomSheet(
                missionTitle = state.selectedMissionInfo.missionTitle,
                missionChangeCount = state.selectedMissionInfo.switchCount,
                eventHandler = viewModel::sendEvent
            )
        }

        if(state.isShowFinishMissionChangeBottomSheet) {
            FinishMissionChangeBottomSheet(
                eventHandler = viewModel::sendEvent
            )
        }
    }
}