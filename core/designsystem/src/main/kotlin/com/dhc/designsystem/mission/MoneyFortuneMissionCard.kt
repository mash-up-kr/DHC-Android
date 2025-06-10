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

/**
 * Todo - Home모듈로 이동
 */
@Composable
fun MoneyFortuneMissionCard(
    missionMode: String,
    missionTitle: String,
    isChecked: Boolean,
    isMissionEnabled: Boolean
) {
    val colors = LocalDhcColors.current
    val missionColor =
        if(isMissionEnabled)
            colors.text.textBodyPrimary
        else SurfaceColor.neutral400

    MissionItemBackGround(
        isChecked = isChecked,
        isEnabled = isMissionEnabled,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = missionTitle,
                    style = DhcTypoTokens.TitleH5,
                    color = missionColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                //TODO - Badge Component 변경
                Text(
                    text = missionMode,
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
            missionTitle = "텀블러 들고 다니기",
            missionMode = "Easy",
            isMissionEnabled = parameter.isMissionEnabled,
            isChecked = parameter.isChecked
        )
    }
}

private class MissionCardPreviewProvider : PreviewParameterProvider<MissionCardPreviewProvider.Parameter> {
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