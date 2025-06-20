package com.dhc.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope

@SuppressLint("ModifierFactoryExtensionFunction")
sealed interface WordBalloonColorType {
    fun bodyBackground(shape: Shape): Modifier

    class SingleColor(private val color: Color) : WordBalloonColorType {
        override fun bodyBackground(shape: Shape): Modifier = Modifier.background(
            color = color,
            shape = shape,
        )

        fun drawPointPath(
            scope: DrawScope,
            path: Path
        ) = scope.drawPath(
            path = path,
            color = color,
        )
    }

    class ColorStops(
        private val backgroundColorStops: Array<Pair<Float, Color>>,
        private val pointColorStops: Array<Pair<Float, Color>>,
    ) : WordBalloonColorType {
        override fun bodyBackground(shape: Shape): Modifier = Modifier.background(
            brush = Brush.verticalGradient(colorStops = backgroundColorStops),
            shape = shape,
        )

        fun drawPointPath(
            scope: DrawScope,
            path: Path,
            startY: Float = 0f,
            endY: Float = Float.POSITIVE_INFINITY,
            tileMode: TileMode = TileMode.Clamp,
        ) = scope.drawPath(
            path = path,
            brush = Brush.verticalGradient(
                colorStops = pointColorStops,
                startY = startY,
                endY = endY,
                tileMode = tileMode,
            ),
        )
    }
}
