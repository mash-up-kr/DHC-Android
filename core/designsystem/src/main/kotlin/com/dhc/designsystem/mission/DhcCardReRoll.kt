package com.dhc.designsystem.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.Typography
import kotlin.math.roundToInt

@Composable
fun CardReRoll(
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var contextMenuWidth by remember { mutableFloatStateOf(0f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isExpanded by remember { mutableStateOf(false) }
    val colors = LocalDhcColors.current

    val draggableState = rememberDraggableState { delta ->
        if (!isExpanded) {
            offsetX = (offsetX + delta).coerceIn(0f, contextMenuWidth)
        } else {
            // 이미 확장된 상태에서 반대방향으로 드래그하면 닫기
            if (delta < 0) {
                offsetX = (offsetX + delta).coerceAtLeast(0f)
                if (offsetX <= contextMenuWidth * 0.3f) {
                    isExpanded = false
                    offsetX = 0f
                }
            }
        }
    }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .onSizeChanged {
                    contextMenuWidth = it.width.toFloat()
                },
        ) {
            actions()
        }
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    state =  draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        if (!isExpanded) {
                            if (offsetX > contextMenuWidth * 0.5f) {
                                isExpanded = true
                                offsetX = contextMenuWidth
                            } else {
                                offsetX = 0f
                            }
                        }
                    }
                ),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PreviewSwipeTest() {
    DhcTheme {
        CardReRoll(
            actions = {
                Box(modifier = Modifier
                    .background(color = AccentColor.violet300)
                    .height(64.dp)
                    .padding(16.dp)
                ) {
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
                    missionMode = "Easy",
                    isMissionEnabled = true,
                    isChecked = true,
                    missionTitle = "돈 아끼기"
                )
            }
        )
    }
}