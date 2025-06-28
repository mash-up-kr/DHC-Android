package com.dhc.home.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
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
        MissionTitle(
            title = stringResource(R.string.spending_habit_mission),
            isInfoIconVisible = true,
            tooltipMessage = "이것은 툴팁 메시지입니다.",
            isEnableTooltip = true,
            spendTypeText = "커피값 절약",
        )
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
            modifier = Modifier.padding(start = 20.dp),
            title = stringResource(R.string.monetary_daily_mission),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // TODO - 추후 itemList로 변경
            val items = listOf<String>("돈 아끼기", "돔황챠돔황챠돔황챠돔황챠돔황챠돔황챠돔황챠돔황챠화이팅","돈 아끼기",)
            items.forEach {
                MissionCardReRoll(
                    missionTitle = it,
                    onClickMissionChange = {}
                )
            }
        }
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