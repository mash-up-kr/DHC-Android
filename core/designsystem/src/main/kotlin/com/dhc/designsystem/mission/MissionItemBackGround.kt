package com.dhc.designsystem.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.borderColorIf
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.check.DhcCheck
import com.dhc.designsystem.check.model.DhcCheckStyle
import com.dhc.designsystem.colors
import kotlinx.coroutines.delay

@Composable
fun MissionItemBackGround(
    isChecked: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    isBlink: Boolean = false,
    onBlinkEnd: () -> Unit = {},
    onCheckChange: () -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    LaunchedEffect(isBlink) {
        if (isBlink) {
            delay(1500)
            onBlinkEnd()
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .borderColorIf(
                width = 2.dp,
                color = AccentColor.violet400,
                shape = RoundedCornerShape(12.dp),
                predicate = { isBlink }
            )
            .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
        Spacer(modifier = Modifier.width(16.dp))
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
            content = {},
            onCheckChange = {}
        )
    }
}