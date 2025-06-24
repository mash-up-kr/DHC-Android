package com.dhc.designsystem.spinner.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
internal fun DhcItemSpinnerContent(
    text: String,
    isFocused: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (isFocused) {
                colors.text.textHighLightsSecondary
            } else {
                SurfaceColor.neutral300
            }.let {
                if (isEnabled) it else it.copy(alpha = 0.4f)
            },
            style = if (isFocused) DhcTypoTokens.TitleH5 else DhcTypoTokens.Body3,
        )
    }
}

private class DhcItemSpinnerContentPreviewProvider : PreviewParameterProvider<DhcItemSpinnerContentPreviewProvider.Parameters> {
    override val values = sequenceOf(
        Parameters(isFocused = true, isEnabled = true),
        Parameters(isFocused = true, isEnabled = false),
        Parameters(isFocused = false, isEnabled = true),
        Parameters(isFocused = false, isEnabled = false),
    )

    data class Parameters(
        val isFocused: Boolean,
        val isEnabled: Boolean,
    )
}

@Preview(showBackground = true)
@Composable
private fun DhcItemSpinnerContentPreview(
    @PreviewParameter(DhcItemSpinnerContentPreviewProvider::class)
    parameter: DhcItemSpinnerContentPreviewProvider.Parameters,
) {
    DhcTheme {
        DhcItemSpinnerContent(
            text = "Item",
            isFocused = parameter.isFocused,
            isEnabled = parameter.isEnabled,
            modifier = Modifier,
        )
    }
}
