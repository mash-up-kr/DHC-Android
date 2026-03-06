package com.example.survey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SurveyRoute(
    navigateToHome: () -> Unit,
    navigateToPrevScreen: () -> Unit,
    viewModel: SurveyViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        } else {
            SurveyScreen(
                state = state,
                navigateToHome = navigateToHome,
                navigateToPrevScreen = navigateToPrevScreen,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
