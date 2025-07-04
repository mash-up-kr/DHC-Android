package com.dhc.designsystem.check.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DhcCheckStyle(
    val containerSize: Dp,
    val iconSize: Dp,
) {
    companion object {
        val Default = DhcCheckStyle(
            containerSize = 24.dp,
            iconSize = 16.dp,
        )
        val Size20Icon12 = DhcCheckStyle(
            containerSize = 20.dp,
            iconSize = 12.dp,
        )
    }
}
