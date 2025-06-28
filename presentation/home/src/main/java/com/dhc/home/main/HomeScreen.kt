package com.dhc.home.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.floatingButton.DhcFloatingButton
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.fortunecard.FlippableBox
import com.dhc.home.R
import com.dhc.home.model.SelectChangeMission
import com.dhc.presentation.mvi.EventHandler

@Composable
fun HomeScreen(
    state: HomeContract.State,
    reRollExpanded: Boolean,
    eventHandler: EventHandler<HomeContract.Event>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onBlinkEnd: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val density = LocalDensity.current
        val topBarSize = WindowInsets.statusBars.getTop(density)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(466.dp)
                .offset(y = -(topBarSize.div(density.density).dp))
                .background(brush = GradientColor.backgroundGradient02Alpha(0.6f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(13.dp))
                Text(
                    text = state.homeInfo.todayDailyFortune.date,
                    style = DhcTypoTokens.Body3,
                    color = SurfaceColor.neutral300,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MonetaryLuckInfo(
                    fortuneScore = state.homeInfo.todayDailyFortune.score,
                    fortuneDetail = state.homeInfo.todayDailyFortune.fortuneDetail,
                    onClickMoreButton = { eventHandler(HomeContract.Event.ClickMoreButton) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(brush = GradientColor.backgroundGradient01)
                        .offset(y = -(10.dp)),
                ) {
                    FlippableBox(
                        isFlipped = false,
                        onFlipEnd = {},
                        frontScreen = {
                            DhcFortuneCard(
                                title = state.homeInfo.todayDailyFortune.fortuneTitle,
                                description = state.homeInfo.todayDailyFortune.fortuneDetail,
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 60.dp)
                                    .size(width = 144.dp, height = 200.dp)
                                    .align(Alignment.Center),
                            )
                        },
                        backScreen = {
                            DhcFortuneCard(
                                title = state.homeInfo.todayDailyFortune.fortuneTitle,
                                description = state.homeInfo.todayDailyFortune.fortuneDetail,
                                modifier = Modifier
                                    .clickable { eventHandler(HomeContract.Event.ClickFortuneCard) }
                                    .padding(top = 20.dp, bottom = 60.dp)
                                    .size(width = 144.dp, height = 200.dp)
                                    .align(Alignment.Center),
                            )
                        },
                        modifier = Modifier.align(Alignment.Center),
                        initialRotationZ = -4f,
                    )
                }
                Spacer(modifier = Modifier.height(13.5.dp))
                SpendingHabitMission(
                    missionUiModel = state.homeInfo.longTermMission,
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            MonetaryLuckyDailyMission(
                dailyMissionList = state.homeInfo.todayDailyMissionList,
                onClickMissionChange = { mission -> eventHandler(HomeContract.Event.ClickMissionChange(
                    SelectChangeMission(
                        missionId = mission.missionId,
                        switchCount = mission.switchCount,
                        missionTitle = mission.title
                    )
                )) },
                onBlinkEnd = onBlinkEnd,
                reRollExpanded = reRollExpanded,
            )
            Spacer(modifier = Modifier.height(136.dp))
        }

        DhcFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 20.dp),
            text = stringResource(R.string.finish_today_mission),
            isEnabled = true,
            onClick = { eventHandler(HomeContract.Event.ClickMissionComplete) },
        )
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    DhcTheme {
        HomeScreen(
            eventHandler = {},
            state = HomeContract.State(),
            isBlink = false,
            changeMissionId = "",
            onBlinkEnd = {},
            reRollExpanded = true
        )
    }
}
