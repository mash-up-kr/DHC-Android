package com.dhc.dhcandroid.navigation

import androidx.compose.ui.graphics.Color

sealed interface ContainerColor {
    data object Default : ContainerColor
    data class ComposeColor(val color: Color) : ContainerColor
}
