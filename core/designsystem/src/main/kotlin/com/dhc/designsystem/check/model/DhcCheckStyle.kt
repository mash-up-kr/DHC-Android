package com.dhc.designsystem.check.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DhcCheckStyle(
    val containerSize: Dp,
    val iconSize: Dp,
) {
    companion object {
        val Default = DhcCheckStyle(
            containerSize = 28.dp,
            iconSize = 16.dp,
        )
        val SnackBar = DhcCheckStyle(
            containerSize = 20.dp,
            iconSize = 12.dp,
        )
    }
}
