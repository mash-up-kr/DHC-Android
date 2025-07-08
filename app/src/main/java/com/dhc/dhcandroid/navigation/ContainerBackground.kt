package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.LocalDhcColors

@Stable
sealed interface ContainerBackground {
    @Composable fun Background(modifier: Modifier)

    data object Default : ContainerBackground {
        @Composable
        override fun Background(modifier: Modifier) {
            Spacer(modifier = modifier.background(LocalDhcColors.current.background.backgroundMain))
        }
    }

    data class ComposeColor(val composeColor: Color) : ContainerBackground {
        @Composable
        override fun Background(modifier: Modifier) {
            Spacer(modifier = modifier.background(composeColor))
        }
    }
}
