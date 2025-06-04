package com.dhc.designsystem

import androidx.compose.ui.graphics.Color

/**
 * 추후 테마 정의 후 삭제 예정
 */
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


object DhcColorTokens {
    object SurfaceColor {
        val Neutral30 = Color(0xFFF4F4F5)
        val Neutral50 = Color(0xFFE6EDF8)
        val Neutral100 = Color(0xFFD7E1EE)
        val Neutral200 = Color(0xFFA5B2C5)
        val Neutral300 = Color(0xFF7B8696)
        val Neutral700 = Color(0xFF1F2127)
        val Neutral800 = Color(0xFF17191F)
        val Neutral900 = Color(0xFF0F1114)
    }
    object AccentColor {
        val Violet50 = Color(0xFFE6E8FF)
        val Violet100 = Color(0xFFCDD1F2)
        val Violet200 = Color(0xFFB5BAEB)
        val Violet300 = Color(0xFF939BE2)
        val Violet400 = Color(0xFF5E69D4)
        val Violet500 = Color(0xFF5660C1)
        val Violet600 = Color(0xFF414BAE)
        val Violet700 = Color(0xFF343FA6)
    }
}

object TextColorTokens {
    val TextMain = DhcColorTokens.SurfaceColor.Neutral30
    val TextBodyPrimary = DhcColorTokens.SurfaceColor.Neutral100
    val TextHighLightsSecondary = DhcColorTokens.AccentColor.Violet200
    val TextHighLightsPrimary = DhcColorTokens.AccentColor.Violet400
}

object BackgroundColorTokens {
    val BackgroundMain = DhcColorTokens.SurfaceColor.Neutral900
}