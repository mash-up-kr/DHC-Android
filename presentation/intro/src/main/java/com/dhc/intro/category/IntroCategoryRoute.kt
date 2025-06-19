package com.dhc.intro.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.designsystem.SurfaceColor

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

    IntroCategoryScreen(
        state = state,
        eventHandler = viewModel::sendEvent,
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceColor.neutral900), // Todo : Theme 적용 완료되면 background 제거하기
    )
}
