package com.dhc.intro.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.mission.MoneyFortuneMissionCard
import com.dhc.designsystem.mission.SpendingHabitMissionCard
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.component.MissionTitle
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroMissionScreen(
    eventHandler: EventHandler<IntroMissionContract.Event>,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(24.dp))

            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_mission_title),
                    titleStyle = DhcTypoTokens.TitleH1,
                ),
                textAlign = TextAlign.Start,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_mission_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            )

            Spacer(modifier = Modifier.height(46.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = scrollState),
            ) {
                MissionTitle(
                    title = stringResource(R.string.spending_habit_mission),
                    isInfoIconVisible = true,
                    spendTypeText = "식음료",
                )
                SpendingHabitMissionCard(
                    missionDday = "D-12",
                    missionTitle = "텀블러 들고 다니기",
                    isChecked = true,
                )

                Spacer(modifier = Modifier.height(24.dp))

                MissionTitle(
                    title = stringResource(R.string.finance_daily_mission),
                )
                Spacer(modifier = Modifier.height(16.dp))
                MoneyFortuneMissionCardList()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceColor.neutral900)
                .align(Alignment.BottomCenter),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(67.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                SurfaceColor.neutral900.copy(alpha = 0f),
                                SurfaceColor.neutral900,
                            ),
                        ),
                    )
            )
            DhcButton(
                text = stringResource(R.string.intro_mission_button),
                buttonSize = DhcButtonSize.XLARGE,
                buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
                onClick = { eventHandler(IntroMissionContract.Event.ClickNextButton) },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MoneyFortuneMissionCardList() {
    val missionStateList = listOf(
        MoneyFortuneMissionState(
            missionMode = "Easy",
            missionTitle = "가까운 거리 걸어다니기",
            isChecked = true,
        ),
        MoneyFortuneMissionState(
            missionMode = "Easy",
            missionTitle = "가까운 거리 걸어다니기",
            isChecked = false,
        ),
        MoneyFortuneMissionState(
            missionMode = "Easy",
            missionTitle = "가까운 거리 걸어다니기",
            isChecked = false,
        ),
        MoneyFortuneMissionState(
            missionMode = "Easy",
            missionTitle = "가까운 거리 걸어다니기",
            isChecked = false,
        ),
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        missionStateList.forEach {
            MoneyFortuneMissionCard(
                missionMode = it.missionMode,
                missionTitle = it.missionTitle,
                isChecked = it.isChecked,
            )
        }
    }
}

private data class MoneyFortuneMissionState(
    val missionMode: String,
    val missionTitle: String,
    val isChecked: Boolean,
)

@Preview(showBackground = true)
@Composable
private fun IntroMissionScreenPreview() {
    DhcTheme {
        IntroMissionScreen(eventHandler = {})
    }
}
