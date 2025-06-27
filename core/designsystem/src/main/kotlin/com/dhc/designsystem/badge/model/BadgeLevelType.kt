package com.dhc.designsystem.badge.model

import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.AccentColor

enum class BadgeLevelType(val color: Color) {
    EASY(Color(0xFF70A2FF)),
    MEDIUM(AccentColor.violet300),
    HARD(Color(0xFFE293A4))
}