package com.dhc.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.home.ui.MissionCompleteCheckBottomSheet
import com.dhc.home.ui.HomeScreen

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box {
        HomeScreen(
            eventHandler = viewModel::sendEvent
        )

        if(state.isShowBottomSheet) {
            MissionCompleteCheckBottomSheet(
                missionCount = 0,
                eventHandler = viewModel::sendEvent
            )
        }
    }
}