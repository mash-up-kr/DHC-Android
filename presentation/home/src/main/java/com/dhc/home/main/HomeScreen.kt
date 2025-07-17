package com.dhc.home.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.floatingButton.DhcFloatingButton
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.home.R
import com.dhc.home.model.SelectChangeMission
import com.dhc.presentation.mvi.EventHandler

@Composable
fun HomeScreen(
    state: HomeContract.State,
    eventHandler: EventHandler<HomeContract.Event>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.homeInfo.todayDailyFortune.date,
                    style = DhcTypoTokens.Body3,
                    color = SurfaceColor.neutral300,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MonetaryLuckInfo(
                    fortuneScore = state.homeInfo.todayDailyFortune.score,
                    fortuneDetail = state.homeInfo.todayDailyFortune.fortuneTitle,
                    onClickMoreButton = { eventHandler(HomeContract.Event.ClickMoreButton) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                DhcFortuneCard(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 12.dp)
                        .align(Alignment.CenterHorizontally)
                        .graphicsLayer(rotationZ = 4f)
                        .clickable { eventHandler(HomeContract.Event.ClickFortuneCard) },
                    title = state.homeInfo.todayDailyFortune.fortuneCardTitle,
                    description = state.homeInfo.todayDailyFortune.fortuneCardSubTitle,
                    imageUrl = state.homeInfo.todayDailyFortune.fortuneCardImage,
                )
                Canvas(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(width = 32.dp, height = 32.dp)
                        .graphicsLayer { scaleX = 4f },
                ) {
                    drawOval(
                        brush = GradientColor.cardBottomGradient01,
                        size = size,
                        alpha = 0.4f,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            SpendingHabitMission(
                missionUiModel = state.homeInfo.longTermMission,
                isFinishedTodayMission = state.homeInfo.todayDone,
                onClickMissionChange = { mission -> eventHandler(HomeContract.Event.ClickMissionChange(
                    SelectChangeMission(
                        missionId = mission.missionId,
                        switchCount = mission.switchCount,
                        missionTitle = mission.title
                    )
                )) },
                onCheckChange = { isChecked, id ->
                    if(!state.homeInfo.todayDone) eventHandler(HomeContract.Event.ClickMissionCheck(isChecked = isChecked, missionId = id)) },
                onExpandedChange = { isExpanded,id -> eventHandler(HomeContract.Event.ChangeExpandCard(isExpanded = isExpanded, missionId = id)) },
                onBlinkEnd = { missionId -> eventHandler(HomeContract.Event.BlinkEnd(missionId))},
            )
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckyDailyMission(
                dailyMissionList = state.homeInfo.todayDailyMissionList,
                isFinishedTodayMission = state.homeInfo.todayDone,
                onClickMissionChange = { mission -> eventHandler(HomeContract.Event.ClickMissionChange(
                    SelectChangeMission(
                        missionId = mission.missionId,
                        switchCount = mission.switchCount,
                        missionTitle = mission.title
                    )
                )) },
                onCheckChange = { isChecked, id ->
                    if(!state.homeInfo.todayDone) eventHandler(HomeContract.Event.ClickMissionCheck(isChecked = isChecked, missionId = id)) },
                onExpandedChange = { isExpanded,id ->eventHandler(HomeContract.Event.ChangeExpandCard(isExpanded = isExpanded, missionId = id)) },
                onBlinkEnd = { missionId -> eventHandler(HomeContract.Event.BlinkEnd(missionId)) },
            )
            Spacer(modifier = Modifier.height(136.dp))
        }

        if(!state.homeInfo.todayDone) {
            DhcFloatingButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 24.dp, end = 20.dp),
                text = stringResource(R.string.finish_today_mission),
                isEnabled = true,
                onClick = { eventHandler(HomeContract.Event.ClickTodayMissionFinish) },
            )
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    DhcTheme {
        HomeScreen(
            eventHandler = {},
            state = HomeContract.State(),
        )
    }
}
