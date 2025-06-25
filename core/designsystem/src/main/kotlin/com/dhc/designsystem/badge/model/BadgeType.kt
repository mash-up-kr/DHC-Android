package com.dhc.designsystem.badge.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

sealed interface BadgeType {
    val cornerRadius: Dp
    val verticalPadding: Dp
    val backgroundColor: Color @Composable get
    val textColor: Color @Composable get
    val textStyle: TextStyle

    data class Level(val isEnabled: Boolean, val level: BadgeLevelType = BadgeLevelType.EASY) : BadgeType {
        override val cornerRadius: Dp = 12.dp
        override val verticalPadding: Dp = 3.dp
        override val backgroundColor: Color
            @Composable get() = if (isEnabled) {
                LocalDhcColors.current.background.backgroundBadgePrimary
            } else {
                LocalDhcColors.current.background.backgroundGlassEffect
            }
        override val textColor: Color
            @Composable get() = if (isEnabled) {
                when(level) {
                    BadgeLevelType.EASY -> { Color(0xFF70A2FF)}
                    BadgeLevelType.MEDIUM -> { AccentColor.violet300 }
                    BadgeLevelType.HARD -> { Color(0xFFE293A4) }
                }
                LocalDhcColors.current.text.textHighLightsPrimary
            } else {
                SurfaceColor.neutral300
            }
        override val textStyle: TextStyle = DhcTypoTokens.Body6
    }

    data object Date : BadgeType {
        override val cornerRadius: Dp = 999999.dp
        override val verticalPadding: Dp = 4.dp
        override val backgroundColor: Color
            @Composable get() = LocalDhcColors.current.background.backgroundGlassEffect
        override val textColor: Color
            @Composable get() = LocalDhcColors.current.text.textBodyPrimary
        override val textStyle: TextStyle = DhcTypoTokens.Body6
    }

    data object SpendType : BadgeType {
        override val cornerRadius: Dp = 999999.dp
        override val verticalPadding: Dp = 4.dp
        override val backgroundColor: Color
            @Composable get() = LocalDhcColors.current.background.backgroundGlassEffect
        override val textColor: Color
            @Composable get() = LocalDhcColors.current.text.textHighLightsSecondary
        override val textStyle: TextStyle = DhcTypoTokens.TitleH8
    }

    data class MissionCard(
        val categoryTextColor: Color,
    ) : BadgeType {
        override val cornerRadius: Dp = 999999.dp
        override val verticalPadding: Dp = 4.dp
        override val backgroundColor: Color
            @Composable get() = SurfaceColor.neutral600
        override val textColor: Color
            @Composable get() = categoryTextColor
        override val textStyle: TextStyle = DhcTypoTokens.Body5
    }
}
