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
import com.dhc.home.model.MissionUiModel
import com.dhc.presentation.component.MissionTitle

@Composable
fun SpendingHabitMission(
    missionUiModel: MissionUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MissionTitle(
            title = stringResource(R.string.spending_habit_mission),
            isInfoIconVisible = true,
            tooltipMessage = stringResource(R.string.mission_tooltip_message),
            isEnableTooltip = true,
            spendTypeText = missionUiModel.category
        )
        Spacer(modifier = Modifier.height(5.dp))
        SpendingHabitMissionCard(
            missionDday = missionUiModel.endDate,
            missionTitle = missionUiModel.title,
            isChecked = missionUiModel.finished,
            isMissionEnabled = !missionUiModel.finished
        )
    }
}


@Composable
fun MonetaryLuckyDailyMission(
    isBlink: Boolean,
    changeMissionId: String,
    onClickMissionChange: (MissionUiModel) -> Unit,
    dailyMissionList: List<MissionUiModel>,
    modifier: Modifier = Modifier,
    onBlinkEnd: () -> Unit
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
            dailyMissionList.forEach { mission ->
                MissionCardReRoll(
                    isBlink = isBlink && changeMissionId == mission.missionId,
                    missionTitle = mission.title,
                    missionMode = mission.difficulty,
                    isChecked = mission.finished,
                    isMissionFinished = mission.finished,
                    onClickMissionChange = { onClickMissionChange(mission) },
                    onBlinkEnd = onBlinkEnd
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
            SpendingHabitMission(
                missionUiModel = MissionUiModel()
            )
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckyDailyMission(
                isBlink = false,
                changeMissionId = "",
                dailyMissionList = listOf(),
                onClickMissionChange = {_ ->},
                onBlinkEnd = {}
            )
        }
    }
}