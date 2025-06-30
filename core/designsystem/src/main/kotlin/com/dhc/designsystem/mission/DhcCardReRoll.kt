package com.dhc.designsystem.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.Typography
import kotlin.math.roundToInt

@Composable
fun DhcCardReRoll(
    actionPadding: Dp = 0.dp,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    actionContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    var contextMenuWidth by remember { mutableFloatStateOf(0f) }
    var offsetX by remember { mutableFloatStateOf(if (isExpanded) contextMenuWidth else 0f) }

    val draggableState = rememberDraggableState { delta ->
        if (!isExpanded) {
            offsetX = (offsetX + delta).coerceIn(0f, contextMenuWidth - (12.dp.value * density.density))
        } else {
            if (delta < 0) {
                offsetX = (offsetX + delta).coerceAtLeast(0f)
            }
        }
    }

    LaunchedEffect(isExpanded) {
        offsetX = if (!isExpanded) {
            0f
        } else {
            contextMenuWidth - (12.dp.value * density.density)
        }
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(top = actionPadding)
                .clip(
                    if(offsetX > 0) RoundedCornerShape(0.dp)
                    else RoundedCornerShape(12.dp)
                )
                .onSizeChanged {
                    contextMenuWidth = it.width.toFloat()
                },
        ) {
            actionContent()
        }

        Box (
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    state =  draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        if (!isExpanded) {
                            if (offsetX > contextMenuWidth * 0.5f) {
                                onExpandedChange(true)
                                offsetX =  contextMenuWidth - (12.dp.value * density.density)
                            } else {
                                offsetX = 0f
                            }
                        } else {
                            onExpandedChange(false)
                            offsetX = 0f
                        }
                    }
                )
                .clip(RoundedCornerShape(12.dp)),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PreviewSwipeTest() {
    DhcTheme {
        DhcCardReRoll(
            isExpanded = false,
            onExpandedChange = {},
            actionContent = {
                Column(
                    modifier = Modifier
                        .background(color = AccentColor.violet300)
                        .clickable { }
                        .height(64.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Icon(
                        painter = painterResource(R.drawable.ico_change),
                        contentDescription = "change",
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "미션 바꾸기",
                        style = Typography.bodyMedium,
                        color = SurfaceColor.neutral500,
                        textAlign = TextAlign.Center
                    )
                }
            },
            content = {
                MoneyFortuneMissionCard(
                    isBlink = true,
                    missionMode = "Easy",
                    isChecked = true,
                    missionTitle = "돈 아끼기"
                )
            }
        )
    }
}