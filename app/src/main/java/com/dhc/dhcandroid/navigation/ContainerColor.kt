package com.dhc.dhcandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.LocalDhcColors

@Stable
sealed interface ContainerColor {
    val color: Color @Composable get

    data object Default : ContainerColor {
        override val color: Color
            @Composable get() = LocalDhcColors.current.background.backgroundMain
    }

    data class ComposeColor(val composeColor: Color) : ContainerColor {
        override val color: Color @Composable get() = composeColor
    }
}
