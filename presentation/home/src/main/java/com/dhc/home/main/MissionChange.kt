package com.dhc.home.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMaxOfOrDefault
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.mission.DhcCardReRoll
import com.dhc.dhcandroid.model.MissionType
import com.dhc.home.R
import com.dhc.home.model.MissionUiModel
import com.dhc.designsystem.R as DR

@Composable
fun MissionCardReRoll(
    type: MissionType,
    canEnabled: Boolean,
    missionUiModel: MissionUiModel,
    onClickMissionChange: () -> Unit,
    modifier: Modifier = Modifier,
    onExpandedChange: (Boolean) -> Unit,
    subComposeMaximumRetry: Int = 2,
    content: @Composable () -> Unit,
) {
    var isExpanded by remember(missionUiModel.missionId) { mutableStateOf(missionUiModel.isExpanded) }
    val targetPadding = if (isExpanded) 16.dp else 0.dp
    val horizontalPadding by animateDpAsState(targetValue = targetPadding, label = "cardPadding")
    val density = LocalDensity.current

    LaunchedEffect(missionUiModel.missionId, missionUiModel.isExpanded) {
        isExpanded = missionUiModel.isExpanded
    }

    SubcomposeLayout(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .graphicsLayer { translationX = -(horizontalPadding.value * density.density) }
    ) { constraints ->
        // content composable 높이 확인
        val contentPlaceableList = subcompose("content") { content() }.map { it.measure(constraints) }
        val contentMaxHeight = contentPlaceableList.fold(0) { maxHeight, placeable ->
            maxOf(maxHeight, placeable.height)
        }

        // content 높이와 actionContent 높이가 일치하는 placeableList 반환
        var number = 0
        var actionContentHeight = contentMaxHeight
        var placeableList: List<Placeable> = emptyList()
        do {
            number++
            actionContentHeight -= placeableList.fastMaxOfOrDefault(contentMaxHeight) { it.height } - contentMaxHeight
            placeableList = subcompose("card$number") {
                DhcCardReRoll(
                    actionTopPadding = if(type == MissionType.LONG_TERM) 12.dp else 0.dp,
                    isExpanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = it
                        onExpandedChange(it)
                    },
                    canEnabled = canEnabled,
                    actionContent = {
                        MissionChange(
                            modifier = Modifier.height(with(density) { actionContentHeight.toDp() }),
                            onClickMissionChange = onClickMissionChange
                        )
                    },
                    content = content
                )
            }.map { it.measure(constraints) }
            // offset 등으로 실제 placeable height 과 content height 값이 다르면 다시 subCompose 시도
        } while (placeableList.maxOf { it.height } != contentMaxHeight && number <= subComposeMaximumRetry)

        // 레이아웃 배치
        val placeableMaxSize = placeableList.fold(IntSize.Zero) { maxSize, placeable ->
            IntSize(
                width = maxOf(maxSize.width, placeable.width),
                height = maxOf(maxSize.height, placeable.height),
            )
        }
        layout(placeableMaxSize.width, placeableMaxSize.height) {
            placeableList.forEach { placeable ->
                placeable.placeRelative(0, 0)
            }
        }
    }
}


@Composable
fun MissionChange(
    onClickMissionChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = modifier
            .background(color = AccentColor.violet400)
            .clickable { onClickMissionChange() }
            .padding(vertical = 10.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Icon(
            painter = painterResource(DR.drawable.ico_change),
            tint = colors.background.backgroundMain,
            contentDescription = "change",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.change_mission),
            style = DhcTypoTokens.Body5,
            color = colors.background.backgroundMain,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PreviewMissionChange() {
    DhcTheme {
        MissionCardReRoll(
            type = MissionType.LONG_TERM,
            canEnabled = true,
            onClickMissionChange = {},
            missionUiModel = MissionUiModel(),
            onExpandedChange = {},
            content = {}
        )
    }
}
