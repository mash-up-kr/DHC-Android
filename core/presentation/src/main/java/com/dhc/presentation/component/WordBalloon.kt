package com.dhc.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors

@Composable
fun WordBalloon(
    modifier: Modifier = Modifier,
    gradientStartColor: Color,
    gradientEndColor: Color,
    cornerWidth: Dp = 12.dp,
    cornerHeight: Dp = 6.dp,
    cornerRadius: Dp = 1.dp, // 꼭짓점 라운드 크기
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.43f to gradientStartColor,
                        1.0f to gradientEndColor,
                    ),
                ),
                shape = RoundedCornerShape(8.dp),
            )
            .drawWithContent {
                drawContent()
                val leftTop = Offset(
                    x = (size.width / 2) - (cornerWidth.toPx() / 2),
                    y = size.height,
                )
                val rightTop = Offset(
                    x = (size.width / 2) + (cornerWidth.toPx() / 2),
                    y = size.height,
                )
                val bottomPoint = Offset(
                    x = size.width / 2,
                    y = size.height + cornerHeight.toPx(),
                )
                val trianglePath = getTrianglePath(
                    leftTop = leftTop,
                    rightTop = rightTop,
                    bottomPoint = bottomPoint,
                    roundRadius = cornerRadius.toPx(),
                )
                drawPath(
                    path = trianglePath,
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to gradientEndColor,
                            0.57f to gradientStartColor,
                        ),
                        startY = leftTop.y,
                        endY = bottomPoint.y
                    ),
                )
            }
            .padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

private fun getTrianglePath(
    leftTop: Offset,
    rightTop: Offset,
    bottomPoint: Offset,
    roundRadius: Float,
): Path = Path().apply {
    moveTo(leftTop.x, leftTop.y)
    lineTo(bottomPoint.x - roundRadius, bottomPoint.y - roundRadius)
    quadraticTo(
        bottomPoint.x,
        bottomPoint.y,
        bottomPoint.x + roundRadius,
        bottomPoint.y - roundRadius,
    )
    lineTo(rightTop.x, rightTop.y)
    close()
}


@Preview
@Composable
private fun WordBalloonPreview() {
    DhcTheme {
        val colors = LocalDhcColors.current
        WordBalloon(
            gradientStartColor = Color(0xFFCFD4DE),
            gradientEndColor = Color(0xFF9BA4D5),
        ) {
            Text(
                text = "Click!",
                style = DhcTypoTokens.TitleH7,
                color = colors.text.textHighLightsPrimary,
            )
        }
    }
}
