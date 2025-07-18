package com.dhc.intro.fortunecard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IntroFortuneCardRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroFortuneCardViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroFortuneCardContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
            }
        }
    }

    IntroFortuneCardScreen(
        state = state,
        modifier = Modifier.fillMaxSize(),
        eventHandler = viewModel::sendEvent
    )
}
