package com.dhc.intro.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.isSplashFinished) {
        if (state.isSplashFinished) {
            navigateToNextScreen()
        }
    }

    SplashScreen(
        modifier = Modifier.fillMaxSize()
    )
}
