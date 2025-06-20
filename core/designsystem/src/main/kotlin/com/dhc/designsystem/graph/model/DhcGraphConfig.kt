package com.dhc.designsystem.graph.model

import androidx.annotation.IntRange

data class DhcGraphConfig(
    @IntRange(from = 1) val maxValue: Int = 100,
    val lineLabels: List<String> = emptyList(),
)
