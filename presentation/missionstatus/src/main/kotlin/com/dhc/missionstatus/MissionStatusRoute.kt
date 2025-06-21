package com.dhc.missionstatus

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.missionstatus.ui.MissionStatusScreen

@Composable
fun MissionStatusRoute(
    viewModel: MissionStatusViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->

        }
    }

    MissionStatusScreen(
        state = state,
        modifier = Modifier.fillMaxSize()
    )
}
