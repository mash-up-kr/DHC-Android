package com.dhc.home.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.home.ui.MissionCompleteCheckBottomSheet
import com.dhc.home.ui.MissionSuccessDialog

@Composable
fun HomeRoute(
    navigateToMission: () -> Unit,
    navigateToMonetaryLuckDetail: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is HomeContract.SideEffect.NavigateToMonetaryDetailScreen -> navigateToMonetaryLuckDetail()
                is HomeContract.SideEffect.NavigateToMission -> navigateToMission()
                is HomeContract.SideEffect.ShowToast -> {}
                is HomeContract.SideEffect.ChangeMissionBoarder -> {}
            }
        }
    }

    Box {
        when (state.homeState) {
            HomeContract.HomeState.Error -> TODO()
            HomeContract.HomeState.FlipCard -> {
                HomeFlipCardScreen(modifier = Modifier.fillMaxSize())
            }
            HomeContract.HomeState.Loading -> {
                HomeLoadingScreen(modifier = Modifier.fillMaxSize())
            }
            HomeContract.HomeState.Success -> {
                HomeScreen(
                    eventHandler = viewModel::sendEvent
                )
            }
        }
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
