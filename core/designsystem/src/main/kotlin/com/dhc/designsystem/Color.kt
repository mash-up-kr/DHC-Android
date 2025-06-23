package com.dhc.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo

internal val colors = DhcColors(
    surface = SurfaceColor,
    accent = AccentColor,
    text = TextColors(
        textMain = SurfaceColor.neutral30,
        textBodyPrimary = SurfaceColor.neutral100,
        textHighLightsSecondary = AccentColor.violet200,
        textHighLightsPrimary = AccentColor.violet400
    ),
    background = BackgroundColors(
        backgroundMain = SurfaceColor.neutral900,
        backgroundGlassEffect = TransparentColor.glassEffect,
        backgroundBadgePrimary = TransparentColor.badgePrimary,
    )
)

data object SurfaceColor {
    val neutral30: Color = Color(0xFFF4F4F5)
    val neutral50: Color = Color(0xFFE6EDF8)
    val neutral100: Color = Color(0xFFD7E1EE)
    val neutral200: Color = Color(0xFFA5B2C5)
    val neutral300: Color = Color(0xFF7B8696)
    val neutral400: Color = Color(0xFF5D6470)
    val neutral500: Color = Color(0xFF3D424B)
    val neutral600: Color = Color(0xFF2A2F38)
    val neutral700: Color = Color(0xFF1F2127)
    val neutral800: Color = Color(0xFF17191F)
    val neutral900: Color = Color(0xFF0F1114)
}

data object AccentColor {
    val violet50: Color = Color(0xFFE6E8FF)
    val violet100: Color = Color(0xFFCDD1F2)
    val violet200: Color = Color(0xFFB5BAEB)
    val violet300: Color = Color(0xFF939BE2)
    val violet400: Color = Color(0xFF5E69D4)
    val violet500: Color = Color(0xFF5660C1)
    val violet600: Color = Color(0xFF414BAE)
    val violet700: Color = Color(0xFF343FA6)
}

data object GradientColor {
    val textGradient01 = Brush.verticalGradient(
        colorStops = arrayOf(
            0.16f to AccentColor.violet200,
            0.83f to SurfaceColor.neutral30,
        ),
    )
    val textGradient02 = Brush.verticalGradient(
        colorStops = arrayOf(
            0.16f to AccentColor.violet200,
            1.0f to SurfaceColor.neutral30,
        ),
    )
    val backgroundGradient01 = Brush.radialGradient(
        colorStops = arrayOf(
            0.23f to AccentColor.violet400,
            0.51f to AccentColor.violet400.copy(alpha = 0.3f),
            0.75f to AccentColor.violet400.copy(alpha = 0.1f),
            0.88f to AccentColor.violet400.copy(alpha = 0.05f),
            1.0f to AccentColor.violet400.copy(alpha = 0f),
        ),
    )
    val backgroundGradient02
        @Composable get() = Brush.radialGradient(
            colorStops = arrayOf(
                0f to AccentColor.violet400.copy(alpha = 0.5f),
                0.15f to AccentColor.violet400.copy(alpha = 0.34f),
                0.4f to AccentColor.violet400.copy(alpha = 0.08f),
                0.67f to AccentColor.violet400.copy(alpha = 0f),
            ),
            center = Offset(x = (LocalWindowInfo.current.containerSize.width * 72 / 100f), y = 0f),
            radius = 1200f,
        )
    val tooltipGradient01 = Brush.verticalGradient(
        colorStops = arrayOf(
            0.43f to SurfaceColor.neutral30,
            1.0f to AccentColor.violet200,
        ),
    )

    @Composable
    fun backgroundGradient02Alpha(alpha: Float): Brush = Brush.radialGradient(
        colorStops = arrayOf(
            0f to AccentColor.violet400.copy(alpha = 0.5f * alpha),
            0.15f to AccentColor.violet400.copy(alpha = 0.34f * alpha),
            0.4f to AccentColor.violet400.copy(alpha = 0.08f * alpha),
            0.67f to AccentColor.violet400.copy(alpha = 0f * alpha),
        ),
        center = Offset(x = (LocalWindowInfo.current.containerSize.width * 72 / 100f), y = 0f),
        radius = 1200f,
    )


    val borderGradient01 = Brush.verticalGradient(
        colorStops = arrayOf(
            0f to AccentColor.violet400.copy(alpha = 0.28f),
            1.0f to SurfaceColor.neutral200.copy(alpha = 0.28f),
        )
    )
}

data object TransparentColor {
    val glassEffect: Color = Color(0x267B8696)
    val badgePrimary: Color = Color(0x335E69D4)
}

data class TextColors(
    val textMain: Color,
    val textBodyPrimary: Color,
    val textHighLightsSecondary: Color,
    val textHighLightsPrimary: Color,
)

data class BackgroundColors(
    val backgroundMain: Color,
    val backgroundGlassEffect: Color,
    val backgroundBadgePrimary: Color,
)

data class DhcColors(
    val surface: SurfaceColor,
    val accent: AccentColor,
    val text: TextColors,
    val background: BackgroundColors,
)
