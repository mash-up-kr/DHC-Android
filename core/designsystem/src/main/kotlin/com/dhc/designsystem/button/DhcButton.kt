package com.dhc.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.common.clickableIf
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle

@Composable
fun DhcButton(
    text: String,
    buttonSize: DhcButtonSize,
    buttonStyle: DhcButtonStyle,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    val backgroundColor =
        if (isEnabled.not()) SurfaceColor.neutral300
        else when (buttonStyle) {
            DhcButtonStyle.PRIMARY -> colors.text.textHighLightsPrimary
            DhcButtonStyle.SECONDARY -> colors.background.backgroundMain // Todo : Glassffect
            DhcButtonStyle.TERITARY -> Color.Transparent
        }
    val textColor =
        if (isEnabled.not()) SurfaceColor.neutral200
        else when (buttonStyle) {
            DhcButtonStyle.PRIMARY -> colors.text.textMain
            DhcButtonStyle.SECONDARY -> colors.text.textMain
            DhcButtonStyle.TERITARY -> SurfaceColor.neutral300
        }

    Text(
        text = text,
        color = textColor,
        style = buttonSize.textStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickableIf(predicate = { isEnabled }) { onClick() }
            .padding(vertical = buttonSize.verticalPadding),
    )
}

private class DhcButtonPreviewProvider : PreviewParameterProvider<DhcButtonPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.PRIMARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.SECONDARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.TERITARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.PRIMARY,
            isEnabled = false,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.MIDDLE,
            buttonStyle = DhcButtonStyle.PRIMARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.MIDDLE,
            buttonStyle = DhcButtonStyle.SECONDARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.MIDDLE,
            buttonStyle = DhcButtonStyle.TERITARY,
            isEnabled = true,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.MIDDLE,
            buttonStyle = DhcButtonStyle.PRIMARY,
            isEnabled = false,
        ),
    )

    data class Parameter(
        val text: String,
        val buttonSize: DhcButtonSize,
        val buttonStyle: DhcButtonStyle,
        val isEnabled: Boolean,
    )
}

@Preview
@Composable
private fun DhcButtonPreview(
    @PreviewParameter(DhcButtonPreviewProvider::class)
    parameter: DhcButtonPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcButton(
            text = parameter.text,
            buttonSize = parameter.buttonSize,
            buttonStyle = parameter.buttonStyle,
            isEnabled = parameter.isEnabled,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
