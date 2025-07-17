package com.dhc.designsystem.score

import androidx.compose.ui.graphics.Brush
import com.dhc.designsystem.GradientColor.fortuneGradientLow
import com.dhc.designsystem.GradientColor.fortuneGradientMid
import com.dhc.designsystem.GradientColor.fortuneGradientTop

fun Int.toGradientScoreColor(): Brush {
   return when (this) {
        in 0 until 41 -> fortuneGradientLow
        in 41 until 71 -> fortuneGradientMid
        in 71..100 -> fortuneGradientTop
        else -> fortuneGradientMid
    }
}