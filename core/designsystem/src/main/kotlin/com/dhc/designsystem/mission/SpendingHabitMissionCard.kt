package com.dhc.designsystem.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcColors
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.colors

@Composable
fun SpendingHabitMissionCard(
    isMissionEnabled: Boolean
) {
    val colors = LocalDhcColors.current
    val missionColor =
        if(isMissionEnabled)
            colors.text.textBodyPrimary
        else SurfaceColor.neutral400

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        MissionItemBackGround(
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    //TODO - Badge Component 변경
                    Text(
                        text = "D-12",
                        style = DhcTypoTokens.TitleH5,
                        color = missionColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "텀블러 들고 다니기",
                        style = DhcTypoTokens.TitleH5,
                        color = missionColor
                    )
                }
            }
        )
        Image(
            modifier = Modifier.align(Alignment.TopStart).offset(x = 8.dp, y = (-12).dp),
            painter = painterResource(R.drawable.pin),
            contentDescription = "mission_card_pin"
        )
    }
}

@Composable
@Preview
private fun PreviewSpendingHabitMissionCard() {
    CompositionLocalProvider(
        LocalDhcColors provides colors
    ) {
        SpendingHabitMissionCard(
            isMissionEnabled = true
        )
    }
}