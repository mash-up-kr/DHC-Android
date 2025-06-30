package com.dhc.designsystem.mission

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType


@Composable
fun MoneyFortuneMissionCard(
    missionMode: String,
    missionTitle: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    isBlink: Boolean = false,
    onBlinkEnd: () -> Unit = {},
    onCheckChange: () -> Unit = {},
    onHeightChanged: (Int) -> Unit = {},
) {
    val colors = LocalDhcColors.current
    val missionColor =
        if(isChecked)
            colors.text.textBodyPrimary
        else SurfaceColor.neutral400

    MissionItemBackGround(
        modifier = modifier.onSizeChanged {
            onHeightChanged(it.height)
        },
        isBlink = isBlink,
        onBlinkEnd = onBlinkEnd,
        isChecked = isChecked,
        onCheckChange = onCheckChange,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                DhcBadge(
                    modifier = Modifier.height(24.dp),
                    text = missionMode,
                    type = BadgeType.Level(isEnabled = isChecked)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = missionTitle,
                    style = DhcTypoTokens.Body3,
                    color = missionColor,
                )
            }
        }
    )
}

@Composable
@Preview
private fun PreviewMoneyFortuneMissionCard(
    @PreviewParameter(MissionCardPreviewProvider::class)
    parameter: MissionCardPreviewProvider.Parameter,
) {
    DhcTheme {
        MoneyFortuneMissionCard(
            missionMode = "Easy",
            isChecked = parameter.isChecked,
            missionTitle = parameter.missionTitle,
            isBlink = false,
        )
    }
}

private class MissionCardPreviewProvider : PreviewParameterProvider<MissionCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            missionTitle = "텀블러 들고 다니기",
            isMissionEnabled = true,
            isChecked = true
        ),
        Parameter(
            missionTitle = "텀블러 들고 다니기텀블러 들고 다니기\"텀블러 들고 다니기\"텀블러 들고 다니기\"텀블러 들고 다니기",
            isMissionEnabled = false,
            isChecked = false
        ),
    )

    data class Parameter(
        val missionTitle: String,
        val isMissionEnabled: Boolean,
        val isChecked: Boolean = false
    )
}
