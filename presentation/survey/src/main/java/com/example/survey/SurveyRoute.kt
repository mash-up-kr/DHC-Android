package com.example.survey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SurveyRoute(
    navigateToHome: () -> Unit,
    navigateToPrevScreen: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SurveyScreen(
            navigateToHome = navigateToHome,
            navigateToPrevScreen = navigateToPrevScreen,
            modifier = Modifier.fillMaxSize(),
       )
    }
}
