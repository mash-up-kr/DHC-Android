package com.dhc.designsystem.mission

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.check.CheckPreviewProvider
import com.dhc.designsystem.check.model.DhcCheckStyle

/**
 * Todo - Home모듈로 이동
 */
@Composable
fun MoneyFortuneMissionCard(
    isMissionEnabled: Boolean
) {
    val colors = LocalDhcColors.current
    val missionColor =
        if(isMissionEnabled)
            colors.text.textBodyPrimary
        else SurfaceColor.neutral400

    MissionItemBackGround(
        content = {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = "텀블러 들고 다니기",
                    style = DhcTypoTokens.TitleH5,
                    color = missionColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                //TODO - Badge Component 변경
                Text(
                    text = "D-12",
                    style = DhcTypoTokens.TitleH5,
                    color = missionColor
                )
            }
        }
    )
}

@Composable
@Preview
private fun PreviewSpendingHabitMissionCard(
    @PreviewParameter(MissionCardPreviewProvider::class)
    parameter: MissionCardPreviewProvider.Parameter,
) {
    DhcTheme {
        MoneyFortuneMissionCard(
            isMissionEnabled = parameter.isMissionEnabled
        )
    }
}

private class MissionCardPreviewProvider : PreviewParameterProvider<MissionCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            isMissionEnabled = true
        ),
        Parameter(
            isMissionEnabled = false
        ),
    )

    data class Parameter(
        val isMissionEnabled: Boolean,
    )
}