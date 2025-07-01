package com.dhc.home.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.mission.MoneyFortuneMissionCard
import com.dhc.designsystem.mission.SpendingHabitMissionCard
import com.dhc.dhcandroid.model.MissionType
import com.dhc.home.R
import com.dhc.home.model.MissionUiModel
import com.dhc.presentation.component.MissionTitle

@Composable
fun SpendingHabitMission(
    missionUiModel: MissionUiModel,
    onExpandedChange: (Boolean, String) -> Unit,
    onClickMissionChange: (MissionUiModel) -> Unit,
    onBlinkEnd: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var missionChangeHeight by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
    ) {
        MissionTitle(
            modifier = Modifier.padding(start = 20.dp),
            title = stringResource(R.string.spending_habit_mission),
            isInfoIconVisible = true,
            tooltipMessage = stringResource(R.string.mission_tooltip_message),
            isEnableTooltip = true,
            spendTypeText = missionUiModel.category
        )
        Spacer(modifier = Modifier.height(5.dp))
        MissionCardReRoll(
            type = MissionType.LONG_TERM,
            modifier = Modifier.padding(top = 12.dp),
            missionChangeHeight = missionChangeHeight,
            missionUiModel = missionUiModel,
            onClickMissionChange = { onClickMissionChange(missionUiModel) },
            onExpandedChange = { onExpandedChange(it, missionUiModel.missionId)},
            content = {
                SpendingHabitMissionCard(
                    missionDday = missionUiModel.endDate,
                    missionTitle = missionUiModel.title,
                    isChecked = !missionUiModel.finished,
                    isMissionEnabled = !missionUiModel.finished,
                    isBlink = missionUiModel.isBlink,
                    onHeightChanged = { missionChangeHeight = it },
                    onBlinkEnd = { onBlinkEnd(missionUiModel.missionId) },
                )
            }
        )
    }
}


@Composable
fun MonetaryLuckyDailyMission(
    onClickMissionChange: (MissionUiModel) -> Unit,
    dailyMissionList: List<MissionUiModel>,
    modifier: Modifier = Modifier,
    onBlinkEnd: (String) -> Unit,
    onExpandedChange: (Boolean, String) -> Unit,
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
                var missionChangeHeight by remember { mutableIntStateOf(0) }
                MissionCardReRoll(
                    type = MissionType.DAILY,
                    missionChangeHeight = missionChangeHeight,
                    missionUiModel = mission,
                    onClickMissionChange = { onClickMissionChange(mission) },
                    onExpandedChange = { onExpandedChange(it, mission.missionId)},
                    content = {
                        MoneyFortuneMissionCard(
                            isBlink = mission.isBlink,
                            missionMode = mission.difficulty,
                            isMissionEnabled = !mission.finished,
                            isChecked = !mission.finished,
                            missionTitle = mission.title,
                            onBlinkEnd = { onBlinkEnd(mission.missionId) },
                            onHeightChanged = { missionChangeHeight = it }
                        )
                    }
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
                missionUiModel = MissionUiModel(),
                onExpandedChange = { _, _ -> },
                onClickMissionChange = { _ -> },
                onBlinkEnd = {}
            )
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckyDailyMission(
                dailyMissionList = listOf(),
                onClickMissionChange = {_ ->},
                onBlinkEnd = {},
                onExpandedChange = { _, _ ->}
            )
        }
    }
}