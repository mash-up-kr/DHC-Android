package com.dhc.intro.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.presentation.component.LottieView
import com.dhc.presentation.mvi.EventHandler

@Composable
fun SplashScreen(
    eventHandler: EventHandler<SplashContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LottieView(
            assetName = "splash_animation.json",
            modifier = Modifier.fillMaxSize(),
            iterations = 1,
            lottieFinished = { eventHandler(SplashContract.Event.LottieAnimationFinished) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    DhcTheme {
        SplashScreen(
            eventHandler = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
