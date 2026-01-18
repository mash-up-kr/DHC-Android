package com.dhc.reward

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RewardRoute(
    modifier: Modifier = Modifier,
    navigateToYearFortune: () -> Unit = {},
    viewModel: RewardViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RewardScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::sendEvent,
        navigateToYearFortune = navigateToYearFortune
    )
}

