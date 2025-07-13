package com.dhc.intro.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IntroRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroStartViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroStartContract.SideEffect.NavigateToNextScreen -> {
                    navigateToNextScreen()
                }
            }
        }
    }

    IntroStartScreen(
        eventHandler = viewModel::sendEvent,
        modifier = Modifier.fillMaxSize(),
    )
}
