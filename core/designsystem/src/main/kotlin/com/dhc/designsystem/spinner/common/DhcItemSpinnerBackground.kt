package com.dhc.designsystem.spinner.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.SurfaceColor

@Composable
internal fun DhcItemSpinnerBackground(
    isFocused: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isFocused) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = if (isEnabled) {
                        SurfaceColor.neutral700
                    } else {
                        SurfaceColor.neutral700.copy(alpha = 0.4f)
                    },
                    shape = RoundedCornerShape(8.dp),
                ),
        )
    } else {
        Spacer(modifier = modifier)
    }
}

private class DhcItemSpinnerBackgroundPreviewProvider : PreviewParameterProvider<DhcItemSpinnerBackgroundPreviewProvider.Parameters> {
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
private fun DhcItemSpinnerBackgroundPreview(
    @PreviewParameter(DhcItemSpinnerBackgroundPreviewProvider::class)
    parameter: DhcItemSpinnerBackgroundPreviewProvider.Parameters,
) {
    DhcTheme {
        DhcItemSpinnerBackground(
            isFocused = parameter.isFocused,
            isEnabled = parameter.isEnabled,
            modifier = Modifier,
        )
    }
}
