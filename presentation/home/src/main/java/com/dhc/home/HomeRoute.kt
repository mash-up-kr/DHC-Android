package com.dhc.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBars
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.dhc.designsystem.DhcSnackBar
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SnackBarContent
import com.dhc.home.main.FinishMissionChangeBottomSheet
import com.dhc.home.main.HomeContract
import com.dhc.home.main.HomeFlipCardScreen
import com.dhc.home.main.HomeLoadingScreen
import com.dhc.home.main.HomeScreen
import com.dhc.home.main.MissionChangeBottomSheet
import com.dhc.home.ui.MissionCompleteCheckBottomSheet
import com.dhc.home.ui.MissionSuccessDialog
import com.dhc.presentation.ui.ErrorScreen
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    navBackStackEntry: NavBackStackEntry,
    navigateToMission: () -> Unit,
    navigateToMonetaryLuckDetail: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(navBackStackEntry)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val topBarSize = WindowInsets.statusBars.getTop(density)

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is HomeContract.SideEffect.NavigateToMonetaryDetailScreen -> navigateToMonetaryLuckDetail()
                is HomeContract.SideEffect.NavigateToMission -> navigateToMission()
                is HomeContract.SideEffect.ShowToast -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(message = sideEffect.msg )
                    }
                }
            }
        }
    }

    Box {
        Crossfade(state.homeState) { currentState ->
            when (currentState) {
                HomeContract.HomeState.Error -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(412.dp)
                                .offset(y = -(topBarSize.div(density.density).dp))
                                .background(brush = GradientColor.backgroundGradient02Alpha(0.6f))
                        )
                        ErrorScreen(
                            onClickRetry = { viewModel.sendEvent(HomeContract.Event.ClickErrorRetryButton) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                HomeContract.HomeState.FlipCard -> {
                    HomeFlipCardScreen(
                        modifier = Modifier.fillMaxSize(),
                        eventHandler = viewModel::sendEvent,
                    )
                }
                HomeContract.HomeState.Loading -> {
                    HomeLoadingScreen(modifier = Modifier.fillMaxSize())
                }
                HomeContract.HomeState.Success -> {
                    HomeScreen(
                        state = state,
                        eventHandler = viewModel::sendEvent,
                    )
                }
            }
        }
        if(state.isShowMissionCompleteBottomSheet) {
            MissionCompleteCheckBottomSheet(
                missionCount = state.remainingMissionCount,
                eventHandler = viewModel::sendEvent
            )
        }

        if(state.isShowMissionSuccessDialog) {
            MissionSuccessDialog(
                savedMoney = state.todaySavedMoney,
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

        DhcSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            snackBarHostState = snackBarHostState,
            snackBarContent = {
                SnackBarContent(
                    snackBarMessage = snackBarHostState.currentSnackbarData?.visuals?.message.orEmpty()
                )
            }
        )
    }
}
