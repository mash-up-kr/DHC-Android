package com.dhc.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieView(
    assetName: String,
    modifier: Modifier = Modifier,
    iterations: Int = Int.MAX_VALUE,
    contentScale: ContentScale = ContentScale.Crop,
    lottieFinished: () -> Unit = {},
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
    )
    LaunchedEffect(progress) {
        if (progress == 1f) { lottieFinished() }
    }
    LottieAnimation(
        composition = composition,
        progress = { progress },
        contentScale = contentScale,
        modifier = modifier,
    )
}
