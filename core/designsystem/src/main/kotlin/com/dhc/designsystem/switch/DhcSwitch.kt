package com.dhc.designsystem.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.switch.model.DhcSwitchColor
import com.dhc.designsystem.switch.model.DhcSwitchSize

@Composable
fun DhcSwitch(
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    switchSize: DhcSwitchSize = DhcSwitchSize.Large,
    animationDurationMs: Int = 250,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = DhcSwitchColor.getColor(isOn, isPressed).backgroundColor,
        animationSpec = tween(durationMillis = animationDurationMs),
    )

    val switchColor by animateColorAsState(
        targetValue = DhcSwitchColor.getColor(isOn, isPressed).switchColor,
        animationSpec = tween(durationMillis = animationDurationMs),
    )

    val alignment by animateDpAsState(
        targetValue = switchSize.getOffset(isOn, horizontalPadding = 2.dp),
        animationSpec = tween(durationMillis = animationDurationMs),
    )

    Box(
        modifier = modifier
            .width(switchSize.width)
            .height(switchSize.height)
            .clip(RoundedCornerShape(switchSize.radius))
            .background(backgroundColor)
            .border(
                width = if (isPressed.not()) 1.dp else 0.dp,
                color = DhcSwitchColor.getColor(isOn, isPressed).borderColor ?: Color.Transparent,
                shape = RoundedCornerShape(switchSize.radius),
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onToggle(!isOn)
            }
            .padding(vertical = 2.dp),
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(x = alignment.roundToPx(), y = 0) }
                .size(switchSize.switchSize)
                .background(switchColor, shape = CircleShape),
        )
    }
}

@Preview
@Composable
private fun DhcSwitchInteractionPreview() {
    DhcTheme {
        var isOn by remember { mutableStateOf(false) }
        DhcSwitch(
            isOn = isOn,
            onToggle = { isOn = it },
        )
    }
}

private class DhcSwitchPreviewProvider : androidx.compose.ui.tooling.preview.PreviewParameterProvider<DhcSwitchPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            isOn = true,
            isEnabled = true,
            switchSize = DhcSwitchSize.Large,
        ),
        Parameter(
            isOn = false,
            isEnabled = true,
            switchSize = DhcSwitchSize.Large,
        ),
        Parameter(
            isOn = true,
            isEnabled = false,
            switchSize = DhcSwitchSize.Large,
        ),
        Parameter(
            isOn = false,
            isEnabled = false,
            switchSize = DhcSwitchSize.Large,
        ),
        Parameter(
            isOn = true,
            isEnabled = true,
            switchSize = DhcSwitchSize.Normal,
        ),
        Parameter(
            isOn = false,
            isEnabled = true,
            switchSize = DhcSwitchSize.Normal,
        ),
        Parameter(
            isOn = true,
            isEnabled = false,
            switchSize = DhcSwitchSize.Normal,
        ),
        Parameter(
            isOn = false,
            isEnabled = false,
            switchSize = DhcSwitchSize.Normal,
        )
    )

    data class Parameter(
        val isOn: Boolean,
        val isEnabled: Boolean,
        val switchSize: DhcSwitchSize,
    )
}

@Preview
@Composable
private fun DhcSwitchPreview(
    @PreviewParameter(DhcSwitchPreviewProvider::class)
    parameter: DhcSwitchPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcSwitch(
            isOn = parameter.isOn,
            switchSize = parameter.switchSize,
            onToggle = {}
        )
    }
}
