package com.dhc.intro.intro

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun IntroRoute(
    navigateToNextScreen: () -> Unit,
) {
    Scaffold { innerPadding ->
        IntroScreen(
            modifier = Modifier.padding(innerPadding),
            navigateToNextScreen = navigateToNextScreen,
        )
    }
}
