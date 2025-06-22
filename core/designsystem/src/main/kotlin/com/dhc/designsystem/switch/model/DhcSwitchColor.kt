package com.dhc.designsystem.switch.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

sealed interface DhcSwitchColor {
    val backgroundColor: Color @Composable get
    val switchColor: Color
    val borderColor: Color?

    data object EnabledOn : DhcSwitchColor {
        override val backgroundColor @Composable get() = AccentColor.violet600
        override val switchColor = SurfaceColor.neutral30
        override val borderColor = null
    }

    data object PressedOn : DhcSwitchColor {
        override val backgroundColor @Composable get() = LocalDhcColors.current.text.textHighLightsPrimary
        override val switchColor = SurfaceColor.neutral30.copy(alpha = 0.5f)
        override val borderColor = null
    }

    data object EnabledOff : DhcSwitchColor {
        override val backgroundColor @Composable get() = LocalDhcColors.current.background.backgroundMain
        override val switchColor = SurfaceColor.neutral600
        override val borderColor = SurfaceColor.neutral500
    }

    data object PressedOff : DhcSwitchColor {
        override val backgroundColor @Composable get() = SurfaceColor.neutral700
        override val switchColor = SurfaceColor.neutral400
        override val borderColor = null
    }

    companion object {
        fun getColor(isOn: Boolean, isPressed: Boolean): DhcSwitchColor {
            return when {
                isOn && !isPressed -> EnabledOn
                isOn && isPressed -> PressedOn
                !isOn && !isPressed -> EnabledOff
                else -> PressedOff
            }
        }
    }
}
