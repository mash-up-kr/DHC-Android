package com.dhc.common

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

/**
 * string Hex to Color
 */
fun hexToColor(hex: String): Color {
    return try {
        Color(hex.toColorInt())
    } catch (e: IllegalArgumentException) {
        Color(0xFFD7E1EE)
    }
}