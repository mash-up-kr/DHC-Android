package com.dhc.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalWindowInfo
import com.dhc.designsystem.AccentColor

@Composable
fun TopGradientBackground(modifier: Modifier = Modifier) {
    val screenWidth = LocalWindowInfo.current.containerSize.width
    Box(
        modifier = modifier
            .background(brush = Brush.radialGradient(
                colorStops = arrayOf(
                    0f to AccentColor.violet400.copy(alpha = 0.3f),
                    0.15f to AccentColor.violet400.copy(alpha = 0.20f),
                    0.4f to AccentColor.violet400.copy(alpha = 0.04f),
                    0.67f to AccentColor.violet400.copy(alpha = 0f),
                ),
                center = Offset(x = (screenWidth * 72 / 100f), y = 0f),
                radius = 1200f
            ))
    )
}
