package com.dhc.intro.description

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.designsystem.SurfaceColor

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
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceColor.neutral900), // Todo : Theme 적용 완료되면 background 제거하기
        eventHandler = viewModel::sendEvent
    )
}
