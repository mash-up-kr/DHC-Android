package com.dhc.designsystem.graph.model

import androidx.compose.ui.graphics.Color
import com.dhc.designsystem.SurfaceColor

data class DhcGraphData(
    val value: Int = 0,
    val label: String = "",
    val color: Color = SurfaceColor.neutral400,
    val tooltipMessage: String = "",
)
