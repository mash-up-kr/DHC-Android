package com.dhc.intro.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.presentation.ui.ErrorScreen

@Composable
fun IntroCategoryRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroCategoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroCategoryContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
            }
        }
    }

    when (state.loadState) {
        IntroCategoryContract.LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        IntroCategoryContract.LoadState.Error -> {
            ErrorScreen(
                onClickRetry = { viewModel.sendEvent(IntroCategoryContract.Event.ClickRetryButton) },
                modifier = Modifier.fillMaxSize(),
            )
        }

        IntroCategoryContract.LoadState.Success -> {
            IntroCategoryScreen(
                state = state,
                eventHandler = viewModel::sendEvent,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
