package com.dhc.intro.mission

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IntroMissionRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroMissionViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroMissionContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
            }
        }
    }

    IntroMissionScreen(
        modifier = Modifier.fillMaxSize(),
        eventHandler = viewModel::sendEvent
    )
}
