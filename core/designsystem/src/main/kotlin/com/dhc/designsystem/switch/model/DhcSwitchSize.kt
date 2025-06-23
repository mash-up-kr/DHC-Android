package com.dhc.designsystem.switch.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface DhcSwitchSize {
    val width: Dp
    val height: Dp
    val radius: Dp
    val switchSize: Dp

    data object Large : DhcSwitchSize {
        override val width = 40.dp
        override val height = 22.dp
        override val radius = 12.dp
        override val switchSize = 18.dp
    }

    data object Normal : DhcSwitchSize {
        override val width = 30.dp
        override val height = 14.dp
        override val radius = 9.dp
        override val switchSize = 10.dp
    }

    fun getOffset(isOn: Boolean, horizontalPadding: Dp): Dp {
        return if (isOn) width - switchSize - horizontalPadding else horizontalPadding
    }
}
