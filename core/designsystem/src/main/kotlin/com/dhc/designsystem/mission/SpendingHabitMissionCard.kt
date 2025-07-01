package com.dhc.designsystem.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
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
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType


@Composable
fun SpendingHabitMissionCard(
    missionDday: String,
    missionTitle: String,
    isChecked: Boolean,
    isMissionEnabled: Boolean,
    isBlink: Boolean = false,
    onBlinkEnd: () -> Unit = {},
    onHeightChanged: (Int) -> Unit = {},
) {
    val colors = LocalDhcColors.current
    val missionColor =
        if(isMissionEnabled)
            colors.text.textBodyPrimary
        else SurfaceColor.neutral400

    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
    ) {
        MissionItemBackGround(
            modifier = Modifier.onSizeChanged{ onHeightChanged(it.height) },
            isChecked = isChecked,
            isEnabled = isMissionEnabled,
            isBlink = isBlink,
            onBlinkEnd = onBlinkEnd,
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                ) {
                    DhcBadge(
                        text = missionDday,
                        type = BadgeType.Level(isEnabled = isMissionEnabled)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = missionTitle,
                        style = DhcTypoTokens.Body3,
                        color = missionColor
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        )
        Image(
            modifier = Modifier.align(Alignment.TopStart).offset(x = (-1).dp, y = (-12).dp),
            painter = painterResource(R.drawable.ico_pin),
            contentDescription = "mission_card_pin",
            colorFilter = ColorFilter.tint(color = SurfaceColor.neutral300),
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
