package com.dhc.designsystem.floatingButton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.common.clickableIf
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcFloatingButton(
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Text(
        text = text,
        style = DhcTypoTokens.TitleH5_1,
        color = if (isEnabled) colors.text.textMain else SurfaceColor.neutral400,
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(999999.dp),
            )
            .background(
                color = if (isEnabled) AccentColor.violet400 else SurfaceColor.neutral500,
                shape = RoundedCornerShape(999999.dp),
            )
            .clickableIf(predicate = { isEnabled }) { onClick() }
            .padding(vertical = 13.dp, horizontal = 20.dp),
    )
}


private class DhcFloatingButtonPreviewProvider : PreviewParameterProvider<DhcFloatingButtonPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            text = "오늘 미션 끝내기",
            isEnabled = true,
        ),
        Parameter(
            text = "오늘 미션 끝내기",
            isEnabled = false,
        ),
    )

    data class Parameter(
        val text: String,
        val isEnabled: Boolean,
    )
}

@Preview
@Composable
private fun DhcFloatingButtonPreview(
    @PreviewParameter(DhcFloatingButtonPreviewProvider::class)
    parameter: DhcFloatingButtonPreviewProvider.Parameter,
) {
    DhcFloatingButton(
        text = parameter.text,
        isEnabled = parameter.isEnabled,
        onClick = {},
    )
}
