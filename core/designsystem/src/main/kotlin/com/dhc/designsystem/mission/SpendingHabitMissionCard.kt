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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

/**
 * Todo - Home모듈로 이동
 */
@Composable
fun SpendingHabitMissionCard(
    missionDday: String,
    missionTitle: String,
    isChecked: Boolean,
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
            isChecked = isChecked,
            isEnabled = isMissionEnabled,
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    //TODO - Badge Component 변경
                    Text(
                        text = missionDday,
                        style = DhcTypoTokens.TitleH5,
                        color = missionColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = missionTitle,
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
private fun PreviewSpendingHabitMissionCard(
    @PreviewParameter(SpendingHabitMissionCardPreviewProvider::class)
    parameter: SpendingHabitMissionCardPreviewProvider.Parameter,
) {
    DhcTheme {
        SpendingHabitMissionCard(
            missionDday = "D-3",
            missionTitle = "텀블러 들고 다니기",
            isChecked = parameter.isChecked,
            isMissionEnabled = parameter.isMissionEnabled
        )
    }
}

private class SpendingHabitMissionCardPreviewProvider :
    PreviewParameterProvider<SpendingHabitMissionCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            isMissionEnabled = true,
            isChecked = true
        ),
        Parameter(
            isMissionEnabled = false,
            isChecked = false
        ),
    )

    data class Parameter(
        val isMissionEnabled: Boolean,
        val isChecked: Boolean = false
    )
}