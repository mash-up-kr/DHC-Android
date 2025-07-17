package com.dhc.designsystem.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.GradientColor.fortuneGradientLow
import com.dhc.designsystem.GradientColor.fortuneGradientMid
import com.dhc.designsystem.GradientColor.fortuneGradientTop
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType

@Composable
fun DhcScoreText(
    badgeText: String?,
    score: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    val textGradientColor = when (score) {
        in 0 until 41 -> fortuneGradientLow
        in 41 until 71 -> fortuneGradientMid
        in 71..100 -> fortuneGradientTop
        else -> fortuneGradientMid
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (badgeText != null) {
            DhcBadge(
                text = badgeText,
                type = BadgeType.Date,
            )
        }
        Text(
            text = stringResource(R.string.d_score, score),
            style = DhcTypoTokens.TitleH0.copy(brush = textGradientColor),
        )
        Text(
            text = description,
            style = DhcTypoTokens.Body3,
            color = colors.text.textBodyPrimary,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun DhcScoreTextPreview() {
    DhcTheme {
        DhcScoreText(
            badgeText = "2025년 5월 20일",
            score = 35,
            description = "마음이 들뜨는 날이에요,\n한템포 쉬어가요.",
        )
    }
}
