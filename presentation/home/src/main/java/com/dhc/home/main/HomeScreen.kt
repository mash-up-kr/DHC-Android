package com.dhc.home.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.ImageResource
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.floatingButton.DhcFloatingButton
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.modal.DhcModal
import com.dhc.home.R
import com.dhc.home.main.component.TodayMissionCompleteTimer
import com.dhc.home.main.component.TodayMissionGoal
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

            TodayMissionCompleteTimer(
                timerText = state.missionTimerText,
                isUnderFourHours = state.isTimerUnderFourHours
            )
            Spacer(modifier = Modifier.height(24.dp))
            TodayMissionGoal(
                modifier = Modifier.padding(horizontal = 20.dp),
                title = state.homeInfo.rewardEvent.rewardEventTitle,
                subtitle = state.homeInfo.rewardEvent.rewardEventSubtitle,
                completedCount = state.homeInfo.rewardEvent.rewardCompletedCount,
                totalCount = state.homeInfo.rewardEvent.rewardTotalCount,
                onClickRewardButton = { eventHandler(HomeContract.Event.ClickRewardButton) }
            )
            Spacer(modifier = Modifier.height(40.dp))
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
                onExpandedChange = { isExpanded, id -> eventHandler(HomeContract.Event.ChangeExpandCard(isExpanded = isExpanded, missionId = id)) },
                onBlinkEnd = { missionId -> eventHandler(HomeContract.Event.BlinkEnd(missionId)) },
            )
            AnimatedVisibility(
                visible = state.isFortuneSurveyVisible,
                enter = expandVertically(),
                exit = shrinkVertically(),
                label = "FortuneSurveyVisibleAnimation",
            ) {
                DhcModal(
                    imageResource = ImageResource.Drawable(resId = R.drawable.fortune_survey_thumbnail),
                    contentScale = ContentScale.FillWidth,
                    onClickClose = { eventHandler(HomeContract.Event.ClickFortuneSurveyClose) },
                    onClickSubmit = { eventHandler(HomeContract.Event.ClickFortuneSurveySubmit) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                )
            }
            Spacer(modifier = Modifier.height(136.dp))
        }

        if(!state.homeInfo.todayDone) {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 24.dp, end = 20.dp)
                    .clickable{ eventHandler(HomeContract.Event.ClickTodayMissionFinish) },
                painter = painterResource(R.drawable.mission_clear_floating),
                contentDescription = "mission_clear_button"
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
