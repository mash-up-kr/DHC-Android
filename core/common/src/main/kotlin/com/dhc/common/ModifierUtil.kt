package com.dhc.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.clickableIf(predicate: () -> Boolean, onClick: () -> Unit) =
    if (predicate()) this.then(Modifier.clickable(onClick = onClick)) else this

fun Modifier.borderIf(width: Dp, brush: Brush, shape: Shape, predicate: () -> Boolean) =
    if (predicate()) this.then(Modifier.border(width, brush, shape)) else this

fun Modifier.drawBalloonTail(
    color: Color,
    cornerWidth: Dp = 12.dp,
    cornerHeight: Dp = 6.dp,
    cornerRadius: Dp = 1.dp,
): Modifier = this.drawWithContent {
    drawContent()
    drawPath(
        path = getTrianglePath(
            cornerWidth = cornerWidth,
            cornerHeight = cornerHeight,
            cornerRadius = cornerRadius,
        ),
        color = color,
    )
}

private fun ContentDrawScope.getTrianglePath(
    cornerWidth: Dp = 12.dp,
    cornerHeight: Dp = 6.dp,
    cornerRadius: Dp = 1.dp,
): Path {
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

    return getTrianglePath(
        leftTop = leftTop,
        rightTop = rightTop,
        bottomPoint = bottomPoint,
        roundRadius = cornerRadius.toPx(),
    )
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
