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
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (isFocused) colors.text.textHighLightsSecondary else SurfaceColor.neutral300,
            style = if (isFocused) DhcTypoTokens.TitleH5 else DhcTypoTokens.Body3,
        )
    }
}

private class DhcItemSpinnerContentPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
private fun DhcItemSpinnerContentPreview(
    @PreviewParameter(DhcItemSpinnerContentPreviewProvider::class)
    isFocused: Boolean,
) {
    DhcTheme {
        DhcItemSpinnerContent(
            text = "Item",
            isFocused = isFocused,
            modifier = Modifier,
        )
    }
}
