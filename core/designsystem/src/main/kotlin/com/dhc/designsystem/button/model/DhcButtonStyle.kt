package com.dhc.designsystem.button.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

sealed interface DhcButtonStyle {
    val backgroundColor: Color @Composable get
    val textColor: Color @Composable get
    val isEnabled: Boolean

    data class Primary(override val isEnabled: Boolean) : DhcButtonStyle {
        override val backgroundColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.text.textHighLightsPrimary
            } else {
                SurfaceColor.neutral300
            }
        override val textColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.text.textMain
            } else {
                SurfaceColor.neutral200
            }
    }

    data class Secondary(override val isEnabled: Boolean) : DhcButtonStyle {
        override val backgroundColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.background.backgroundGlassEffect
            } else {
                SurfaceColor.neutral500
            }
        override val textColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.text.textMain
            } else {
                SurfaceColor.neutral300
            }

    }

    data object Teritary : DhcButtonStyle {
        override val backgroundColor: Color
            @Composable get() = Color.Transparent
        override val textColor: Color
            @Composable get() = SurfaceColor.neutral300
        override val isEnabled: Boolean = true
    }

    data class Small(override val isEnabled: Boolean) : DhcButtonStyle {
        override val backgroundColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.text.textHighLightsPrimary
            } else {
                LocalDhcColors.current.background.backgroundGlassEffect
            }
        override val textColor: Color
            @Composable get() = LocalDhcColors.current.text.textBodyPrimary
    }
}
