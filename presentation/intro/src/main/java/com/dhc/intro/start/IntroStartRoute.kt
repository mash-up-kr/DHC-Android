package com.dhc.intro.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IntroRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.page) {
        if (state.isPageFinished) {
            navigateToNextScreen()
        }
    }

    IntroScreen(
        modifier = Modifier.fillMaxSize(),
        eventHandler = viewModel::sendEvent
    )
}
