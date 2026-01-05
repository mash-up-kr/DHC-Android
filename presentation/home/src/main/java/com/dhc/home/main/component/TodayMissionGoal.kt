package com.dhc.home.main.component

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.dhc.designsystem.TransparentColor
import com.dhc.designsystem.reward.RewardProgressBar

@Composable
fun TodayMissionGoal(
    title: String,
    subtitle: String,
    completedCount: Int,
    totalCount: Int,
    onClickRewardButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    val animatedStep by animateIntAsState(
        targetValue = completedCount,
        animationSpec = tween(durationMillis = 500),
        label = "step"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceColor.neutral700)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = SurfaceColor.neutral500,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(6.dp),
                        painter = painterResource(id = R.drawable.ico_fireworks),
                        contentDescription = "Mission Icon",
                        tint = Color.Unspecified,
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        style = DhcTypoTokens.TitleH5,
                        color = colors.text.textMain,
                    )
                    Text(
                        text = subtitle,
                        style = DhcTypoTokens.Body5,
                        color = colors.text.textBodyPrimary,
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(colors.background.backgroundGlassEffect)
                        .clickable { onClickRewardButton() }
                        .padding(start = 12.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "리워드",
                        style = DhcTypoTokens.Body6,
                        color = colors.text.textBodyPrimary,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ico_arrow_right),
                        contentDescription = "Arrow Right",
                        tint = colors.text.textBodyPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            RewardProgressBar(
                currentStep = animatedStep
            )
        }
    }
}


private class TodayMissionGoalPreviewProvider : PreviewParameterProvider<TodayMissionGoalPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "리워드 2배 이벤트",
            subtitle = "단 3개만 도전해 보세요",
            completedCount = 0,
            totalCount = 3
        ),
        Parameter(
            title = "리워드 2배 이벤트",
            subtitle = "리워드까지 2개 남았어요!",
            completedCount = 1,
            totalCount = 3
        ),
        Parameter(
            title = "리워드 2배 이벤트",
            subtitle = "리워드까지 1개 남았어요!",
            completedCount = 2,
            totalCount = 3
        ),
        Parameter(
            title = "웰컴백 이벤트",
            subtitle = "리워드를 받아 보세요!",
            completedCount = 3,
            totalCount = 3
        ),
    )

    data class Parameter(
        val title: String,
        val subtitle: String,
        val completedCount: Int,
        val totalCount: Int,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun TodayMissionGoalPreview(
    @PreviewParameter(TodayMissionGoalPreviewProvider::class)
    parameter: TodayMissionGoalPreviewProvider.Parameter,
) {
    DhcTheme {
        TodayMissionGoal(
            title = parameter.title,
            subtitle = parameter.subtitle,
            completedCount = parameter.completedCount,
            totalCount = parameter.totalCount,
            onClickRewardButton = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
