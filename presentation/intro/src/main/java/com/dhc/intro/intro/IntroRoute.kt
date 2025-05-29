package com.dhc.intro.intro

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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

    Scaffold { innerPadding ->
        IntroScreen(
            modifier = Modifier.padding(innerPadding),
            eventHandler = viewModel::sendEvent
        )
    }
}
