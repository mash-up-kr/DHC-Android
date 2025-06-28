package com.dhc.common

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object BrushUtil {
    fun solidColor(color: Color): Brush = Brush.linearGradient(colors = listOf(color, color))
}
