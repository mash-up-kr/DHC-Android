package com.dhc.designsystem.floatingButton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.common.FullRoundedCornerShape
import com.dhc.common.borderIf
import com.dhc.common.clickableIf
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
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
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = FullRoundedCornerShape,
            )
            .borderIf(
                predicate = { isEnabled },
                width = 1.dp,
                brush = GradientColor.buttonBorderGradient01,
                shape = FullRoundedCornerShape,
            )
            .clickableIf(predicate = { isEnabled }) { onClick() },
    ) {
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = if (isEnabled) AccentColor.violet500 else SurfaceColor.neutral500,
                    shape = FullRoundedCornerShape,
                )
        )
        if (isEnabled) {
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        translationY = size.height / 2
                        scaleX = 1.5f
                    },
            ) {
                drawOval(
                    brush = GradientColor.buttonSurfaceGradient02(
                        centerOffset = Offset.Unspecified,
                        radius = 100f,
                    ),
                    size = Size(
                        width = size.width,
                        height = size.height * 2,
                    ),
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(vertical = 13.dp, horizontal = 20.dp)
                .align(Alignment.Center),
            text = text,
            style = DhcTypoTokens.TitleH5_1,
            color = if (isEnabled) colors.text.textMain else SurfaceColor.neutral400,
        )
    }
}

private class DhcFloatingButtonPreviewProvider :
    PreviewParameterProvider<DhcFloatingButtonPreviewProvider.Parameter> {
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
    DhcTheme {
        DhcFloatingButton(
            text = parameter.text,
            isEnabled = parameter.isEnabled,
            onClick = {},
        )
    }
}
