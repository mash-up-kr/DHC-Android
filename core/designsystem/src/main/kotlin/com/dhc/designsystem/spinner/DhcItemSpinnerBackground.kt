package com.dhc.designsystem.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme

@Composable
fun DhcItemSpinnerBackground(
    isFocused: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isFocused) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Cyan, RoundedCornerShape(8.dp)),
        )
    } else {
        Spacer(modifier = modifier)
    }
}

class DhcItemSpinnerBackgroundPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
fun DhcItemSpinnerBackgroundPreview(@PreviewParameter(DhcItemSpinnerBackgroundPreviewProvider::class) isFocused: Boolean) {
    DhcTheme {
        DhcItemSpinnerBackground(
            isFocused = isFocused,
            modifier = Modifier,
        )
    }
}
