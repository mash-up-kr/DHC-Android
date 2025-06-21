package com.dhc.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.mission.MoneyFortuneMissionCard
import com.dhc.designsystem.mission.SpendingHabitMissionCard
import com.dhc.home.R
import com.dhc.presentation.component.MissionTitle

@Composable
fun SpendingHabitMission(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(46.dp))

            MissionTitle(
                title = stringResource(R.string.spending_habit_mission),
                isInfoIconVisible = true,
                tooltipMessage = stringResource(R.string.mission_tooltip_message),
                isEnableTooltip = true,
                spendTypeText = "커피값 절약",
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        SpendingHabitMissionCard(
            missionDday = "D-3",
            missionTitle = "텀블러 들고 다니기",
            isChecked = true,
            isMissionEnabled = true
        )
    }
}


@Composable
fun MonetaryLuckyDailyMission(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        MissionTitle(
            title = stringResource(R.string.monetary_daily_mission),
        )
        Spacer(modifier = Modifier.height(16.dp))
        // TODO - 추후 itemList로 변경
        repeat(3) {
            MoneyFortuneMissionCard(
                missionMode = "Easy",
                isMissionEnabled = true,
                isChecked = true,
                missionTitle = "hihihi"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(136.dp))
    }
}

@Preview
@Composable
private fun PreviewMonetaryLuckyDailyMission() {
    DhcTheme {
        Column {
            SpendingHabitMission()
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckyDailyMission()
        }
    }
}