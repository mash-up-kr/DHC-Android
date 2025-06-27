package com.dhc.designsystem.floatingButton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.common.FullRoundedCornerShape
import com.dhc.common.clickableIf
import com.dhc.common.toPx
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
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
                elevation = 4.dp,
                shape = FullRoundedCornerShape,
            )
            .background(color = AccentColor.violet500)
            .background(
                brush = if (isEnabled) {
                    GradientColor.buttonSurfaceGradient02(
                        centerOffset = Offset(
                            x = 72.dp.toPx(),
                            y = 58.dp.toPx(),
                        ),
                        radius = 130f,
                    )
                } else {
                    Brush.linearGradient(colors = listOf(SurfaceColor.neutral500))
                },
                shape = FullRoundedCornerShape,
            )
            .border(
                width = 1.dp,
                brush = GradientColor.buttonBorderGradient01,
                shape = FullRoundedCornerShape,
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
