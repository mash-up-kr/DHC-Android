package com.dhc.designsystem.reward

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.TransparentColor

@Composable
fun RewardProgressBar(
    currentStep: Int,
    modifier: Modifier = Modifier,
    totalStepList: List<String> = listOf("시작", "1개", "2개", "Goal"),
) {
    val colors = LocalDhcColors.current

    val progressWidthByStep = listOf(0.094f, 0.37f, 0.66f, 1.0f)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(TransparentColor.badgePrimary)
        ) {
            val progressWidth = progressWidthByStep.getOrElse(currentStep) { 0.08f }
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressWidth)
                    .height(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.text.textHighLightsPrimary)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                totalStepList.forEachIndexed { index, _ ->
                    val isFilled = index <= currentStep
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = if (isFilled) colors.text.textHighLightsPrimary else colors.text.textHighLightsPrimary,
                                shape = CircleShape
                            )
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            totalStepList.forEachIndexed { index, label ->
                val isCompleted = index <= currentStep
                val isGoal = index == totalStepList.lastIndex

                val textColor = when {
                    isGoal -> colors.text.textMain
                    isCompleted -> colors.text.textHighLightsPrimary
                    else -> SurfaceColor.neutral500
                }

                Text(
                    text = label,
                    style = DhcTypoTokens.TitleH7,
                    color = textColor,
                )
            }
        }
    }
}