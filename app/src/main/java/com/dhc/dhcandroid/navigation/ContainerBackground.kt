package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.GradientColor
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

    data class BackgroundWithTopRightGradientColor(val composeColor: Color? = null) : ContainerBackground {
        @Composable
        override fun Background(modifier: Modifier) {
            Box(modifier = modifier.background(composeColor ?: LocalDhcColors.current.background.backgroundMain)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(412.dp)
                        .align(Alignment.TopCenter)
                        .background(brush = GradientColor.backgroundGradient02Alpha(0.6f))
                )
            }
        }
    }
}
