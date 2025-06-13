package com.dhc.designsystem.progress

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme

@Composable
fun DhcProgressIndicator(
    strokeWidth: Dp,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    progressAnimationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(durationMillis = 1000, easing = LinearEasing),
    ),
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimateFloat =
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = progressAnimationSpec,
        )

    Canvas(
        modifier = modifier
            .graphicsLayer {
                rotationZ = rotationAnimateFloat.value
                compositingStrategy = CompositingStrategy.Offscreen
            },
    ) {
        drawArc(
            brush = Brush.sweepGradient(colors = colors),
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
        )
        drawCircle(
            color = Color.Transparent,
            center = center,
            radius = (size.width / 2) - strokeWidth.toPx(),
            blendMode = BlendMode.Clear
        )
    }
}

@Preview
@Composable
private fun DhcProgressIndicatorPreview() {
    DhcTheme {
        DhcProgressIndicator(
            modifier = Modifier.size(48.dp),
            colors = listOf(Color(0xFF2D2D2D), Color.White),
            strokeWidth = 8.dp,
        )
    }
}
