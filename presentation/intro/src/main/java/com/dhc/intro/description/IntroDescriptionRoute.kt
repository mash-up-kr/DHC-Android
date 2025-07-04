package com.dhc.intro.description

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IntroDescriptionRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroDescriptionViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroDescriptionContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
            }
        }
    }

    IntroDescriptionScreen(
        modifier = Modifier.fillMaxSize(),
        eventHandler = viewModel::sendEvent
    )
}
