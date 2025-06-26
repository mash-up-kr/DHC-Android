package com.dhc.home.main

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.mission.DhcCardReRoll
import com.dhc.designsystem.mission.MoneyFortuneMissionCard
import com.dhc.home.R
import com.dhc.designsystem.R as DR

@Composable
fun MissionCardReRoll(
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val horizontalPadding = if (isExpanded) 0.dp else 16.dp
    DhcCardReRoll(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        isExpanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        actions = {
            MissionChange()
        },
        content = {
            MoneyFortuneMissionCard(
                missionMode = "Easy",
                isMissionEnabled = true,
                isChecked = true,
                missionTitle = "돈 아끼기"
            )
        }
    )
}


@Composable
fun MissionChange() {
    val colors = LocalDhcColors.current
    Column(
        modifier = Modifier
            .background(color = AccentColor.violet400)
            .clickable { }
            .padding(vertical = 10.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Icon(
            painter = painterResource(DR.drawable.ico_change),
            tint = SurfaceColor.neutral30,
            contentDescription = "change",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.change_mission),
            style = DhcTypoTokens.Body5,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PreviewMissionChange() {
    DhcTheme {
        MissionCardReRoll(
        )
    }
}