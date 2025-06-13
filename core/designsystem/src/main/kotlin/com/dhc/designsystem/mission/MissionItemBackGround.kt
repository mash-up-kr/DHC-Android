package com.dhc.designsystem.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.check.DhcCheck
import com.dhc.designsystem.check.model.DhcCheckStyle
import com.dhc.designsystem.colors

@Composable
fun MissionItemBackGround(
    isChecked: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = LocalDhcColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.background.backgroundMain, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
        DhcCheck(
            isChecked = isChecked,
            isEnabled = isEnabled,
            dhcCheckStyle = DhcCheckStyle.Default
        )
    }
}

@Preview
@Composable
private fun PreviewMissionItem() {
    CompositionLocalProvider(
        LocalDhcColors provides colors
    ) {
        MissionItemBackGround(
            isChecked = true,
            isEnabled = true,
            content = {}
        )
    }
}