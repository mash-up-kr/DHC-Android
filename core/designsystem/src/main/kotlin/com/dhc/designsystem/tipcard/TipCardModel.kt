package com.dhc.designsystem.tipcard

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.dhc.designsystem.SurfaceColor

/**
 * 임시 UI모델로 서버 리스펀스에 따라 추후 변경
 */
data class TipCardModel(
    val title: String,
    val cont: String,
    val icon: String,
    val color: String?
)


fun hexToColor(hex: String): Color {
    return try {
        Color(hex.toColorInt())
    } catch (e: IllegalArgumentException) {
        SurfaceColor.neutral100
    }
}