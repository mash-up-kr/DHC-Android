package com.dhc.designsystem.fortunecard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun FlippableBox(
    onFlipEnd: () -> Unit,
    frontScreen: @Composable () -> Unit,
    backScreen: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    minRotationAngle: Float = 0f,
    maxRotationAngle: Float = 180f,
    flipThreshold: Float = 90f,
    initialRotationZ: Float = 0f,
) {
    var canRotate by remember { mutableStateOf(true) }
    var rotationAngleState by remember { mutableFloatStateOf(0f) }
    val animatedRotationAngleState by animateFloatAsState(targetValue = rotationAngleState, label = "")
    val isFront by remember(animatedRotationAngleState) {
        derivedStateOf {
            val angle = animatedRotationAngleState % 360
            angle <= 90f || angle >= 270f
        }
    }
    LaunchedEffect(canRotate) {
        if (canRotate.not()) { onFlipEnd() }
    }

    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    if (canRotate) {
                        rotationAngleState = (rotationAngleState + maxRotationAngle)
                            .coerceIn(minRotationAngle, maxRotationAngle)
                            .also { canRotate = false }
                    }
                }
            )
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        rotationAngleState = (if (rotationAngleState < flipThreshold) 0f else 180f)
                            .also { if (it == 180f) canRotate = false }
                    },
                    onHorizontalDrag = { _, dragAmount ->
                        if (canRotate) {
                            rotationAngleState = (rotationAngleState + dragAmount)
                                .coerceIn(minRotationAngle, maxRotationAngle)
                        }
                    },
                )
            }
            .graphicsLayer {
                rotationY = animatedRotationAngleState + if (isFront) 0f else 180f
                rotationZ = initialRotationZ - (initialRotationZ * (animatedRotationAngleState / maxRotationAngle))
                cameraDistance = 15f
            }
    ) {
        if (isFront) frontScreen() else backScreen()
    }
}
