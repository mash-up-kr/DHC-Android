package com.dhc.reward

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RewardRoute(
    modifier: Modifier = Modifier,
    navigateToYearFortune: () -> Unit = {},
) {
    RewardScreen(
        modifier = modifier,
        navigateToYearFortune = navigateToYearFortune
    )
}

