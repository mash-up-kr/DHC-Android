package com.dhc.reward

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RewardRoute(
    modifier: Modifier = Modifier,
    navigateToYearFortune: (isSampleData: Boolean) -> Unit = {},
    viewModel: RewardViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is RewardContract.SideEffect.NavigateToYearFortune -> {
                    navigateToYearFortune(sideEffect.isSampleData)
                }
                is RewardContract.SideEffect.ShowToast -> {
                    // TODO: Show toast
                }
            }
        }
    }

    RewardScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::sendEvent,
        navigateToYearFortune = {}
    )
}

