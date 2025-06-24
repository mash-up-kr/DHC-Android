package com.dhc.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dhc.common.clickableIf
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle

@Composable
fun DhcButton(
    text: String,
    buttonSize: DhcButtonSize,
    buttonStyle: DhcButtonStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = buttonStyle.textColor,
        style = buttonSize.textStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(buttonSize.cornerRadius))
            .background(
                color = buttonStyle.backgroundColor,
                shape = RoundedCornerShape(buttonSize.cornerRadius)
            )
            .clickableIf(predicate = { buttonStyle.isEnabled }) { onClick() }
            .padding(vertical = buttonSize.verticalPadding),
    )
}

private class DhcButtonPreviewProvider : PreviewParameterProvider<DhcButtonPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Teritary,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = false),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = false),
        ),

        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Teritary,
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = false),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = false),
        ),

        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.SMALL,
            buttonStyle = DhcButtonStyle.Small(isEnabled = true),
        ),
        Parameter(
            text = "금전운 확인하고 시작하기",
            buttonSize = DhcButtonSize.SMALL,
            buttonStyle = DhcButtonStyle.Small(isEnabled = false),
        ),
    )

    data class Parameter(
        val text: String,
        val buttonSize: DhcButtonSize,
        val buttonStyle: DhcButtonStyle,
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
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
