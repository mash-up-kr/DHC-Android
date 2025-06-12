package com.dhc.designsystem.checkbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.check.DhcCheck
import com.dhc.designsystem.check.model.DhcCheckStyle

@Composable
fun DhcCheckButton(
    text: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Row(
        modifier = modifier
            .background(
                color = if (isChecked) colors.background.backgroundBadgePrimary else colors.background.backgroundGlassEffect,
                shape = RoundedCornerShape(4.dp),
            )
            .padding(top = 8.dp, start = 12.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DhcCheck(
            isChecked = isChecked,
            isEnabled = true,
            dhcCheckStyle = DhcCheckStyle(
                containerSize = 20.dp,
                iconSize = 12.dp,
            ),
        )
        Text(
            text = text,
            style = DhcTypoTokens.TitleH7,
            color = colors.text.textBodyPrimary,
        )
    }
}

private class CheckButtonPreviewProvider : PreviewParameterProvider<CheckButtonPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            text = "텍스트",
            isChecked = true,
        ),
        Parameter(
            text = "텍스트",
            isChecked = false,
        ),
    )

    data class Parameter(
        val text: String,
        val isChecked: Boolean,
    )
}

@Preview
@Composable
private fun DhcCheckButtonPreview(
    @PreviewParameter(CheckButtonPreviewProvider::class)
    parameter: CheckButtonPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcCheckButton(
            text = parameter.text,
            isChecked = parameter.isChecked,
        )
    }
}
