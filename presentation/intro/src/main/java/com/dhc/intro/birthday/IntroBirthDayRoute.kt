package com.dhc.intro.birthday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.designsystem.SurfaceColor

@Composable
fun IntroBirthDayRoute(
    navigateToNextScreen: () -> Unit,
    navigateToBackScreen: () -> Unit,
    viewModel: IntroBirthDayViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroBirthDayContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
                IntroBirthDayContract.SideEffect.NavigateBackScreen -> navigateToBackScreen()
            }
        }
    }

    IntroBirthDayScreen(
        eventHandler = viewModel::sendEvent,
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceColor.neutral900), // Todo : Theme 적용 완료되면 background 제거하기
    )
}
