package com.dhc.designsystem.spinner

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dhc.designsystem.DHCAndroidTheme

@Composable
fun DhcItemSpinnerContent(
    text: String,
    isFocused: Boolean,
    modifier: Modifier = Modifier,
) {
    // Todo : design system 적용하기
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (isFocused) Color(0xFF000000) else Color(0xFF999999),
        )
    }
}

class DhcItemSpinnerContentPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
fun DhcItemSpinnerContentPreview(@PreviewParameter(DhcItemSpinnerContentPreviewProvider::class) isFocused: Boolean) {
    DHCAndroidTheme {
        DhcItemSpinnerContent(
            text = "Item",
            isFocused = isFocused,
            modifier = Modifier,
        )
    }
}
