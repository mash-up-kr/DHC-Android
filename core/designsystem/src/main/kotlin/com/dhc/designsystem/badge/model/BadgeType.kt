package com.dhc.designsystem.badge.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

sealed interface BadgeType {
    val cornerRadius: Dp
    val backgroundColor: Color @Composable get
    val textColor: Color @Composable get
    val textStyle: TextStyle

    data class Level(val isEnabled: Boolean) : BadgeType {
        override val cornerRadius: Dp = 12.dp
        override val backgroundColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.background.backgroundBadgePrimary
            } else {
                LocalDhcColors.current.background.backgroundGlassEffect
            }
        override val textColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.text.textHighLightsPrimary
            } else {
                SurfaceColor.neutral500
            }
        override val textStyle: TextStyle = DhcTypoTokens.Body7
    }

    data object Date : BadgeType {
        override val cornerRadius: Dp = 999999.dp
        override val backgroundColor: Color
            @Composable get() = LocalDhcColors.current.background.backgroundGlassEffect
        override val textColor: Color
            @Composable get() = LocalDhcColors.current.text.textBodyPrimary
        override val textStyle: TextStyle = DhcTypoTokens.Body6
    }

    data object SpendType : BadgeType {
        override val cornerRadius: Dp = 999999.dp
        override val backgroundColor: Color
            @Composable get() = LocalDhcColors.current.background.backgroundGlassEffect
        override val textColor: Color
            @Composable get() = LocalDhcColors.current.text.textHighLightsSecondary
        override val textStyle: TextStyle = DhcTypoTokens.TitleH8
    }
}
