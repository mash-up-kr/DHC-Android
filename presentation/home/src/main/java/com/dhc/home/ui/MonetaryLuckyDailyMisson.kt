package com.dhc.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType
import com.dhc.designsystem.mission.MoneyFortuneMissionCard
import com.dhc.designsystem.mission.SpendingHabitMissionCard
import com.dhc.home.R
import com.dhc.designsystem.R as designR

@Composable
fun SpendingHabitMission(
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "소비습관 미션",
                style = DhcTypoTokens.TitleH4_1,
                color = colors.text.textBodyPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(designR.drawable.ico_info_circle),
                tint = SurfaceColor.neutral400,
                contentDescription = "info"
            )
            Spacer(modifier = Modifier.width(12.dp))
            DhcBadge(
                text = "커피값 절약",
                type = BadgeType.SpendType,
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
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
    ) {
        Text(
            text = "금전운 기반 일일 미션",
            style = DhcTypoTokens.TitleH4_1,
            color = colors.text.textBodyPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(3) {
                MoneyFortuneMissionCard(
                    missionMode = "Easy",
                    isMissionEnabled = true,
                    isChecked = true,
                    missionTitle = "hihihi"
                )
                Spacer(modifier = Modifier.height(8.dp))
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