package com.dhc.designsystem.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType

@Composable
fun DhcMissionInfoCard(
    categoryText: String,
    categoryTextColor: Color,
    message: String,
    currentMissionCount: Int,
    totalMissionCount: Int,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .background(SurfaceColor.neutral700, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        DhcBadge(
            text = categoryText,
            type = BadgeType.MissionCard(categoryTextColor),
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = message,
            color = colors.text.textMain,
            style = DhcTypoTokens.TitleH5_1,
        )

        Row(
            modifier = Modifier.padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = currentMissionCount.toString(),
                color = colors.text.textBodyPrimary,
                style = DhcTypoTokens.TitleH3,
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 8.dp)
                    .size(width = 1.dp, height = 12.dp)
                    .background(SurfaceColor.neutral500)
            )
            Text(
                text = totalMissionCount.toString(),
                color = SurfaceColor.neutral300,
                style = DhcTypoTokens.Body1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DhcMissionInfoCardPreview() {
    DhcTheme {
        DhcMissionInfoCard(
            modifier = Modifier.width(161.dp),
            categoryText = "일일 미션",
            categoryTextColor = Color(0xFF4CAF50), // 예시로 녹색 사용
            message = "오늘의 미션을\n완료하세요!",
            currentMissionCount = 3,
            totalMissionCount = 5,
        )
    }
}
