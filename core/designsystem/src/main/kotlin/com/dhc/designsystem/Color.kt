package com.dhc.designsystem

import androidx.compose.ui.graphics.Color

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
        backgroundMain = SurfaceColor.neutral900
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

data class TextColors(
    val textMain: Color,
    val textBodyPrimary: Color,
    val textHighLightsSecondary: Color,
    val textHighLightsPrimary: Color
)
data class BackgroundColors(
    val backgroundMain: Color
)


data class DhcColors(
    val surface: SurfaceColor,
    val accent: AccentColor,
    val text: TextColors,
    val background: BackgroundColors
)
