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
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType

@Composable
fun DhcScoreText(
    badgeText: String,
    score: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    DhcScoreText(
        badgeText = badgeText,
        score = stringResource(R.string.d_score, score),
        description = description,
        modifier = modifier,
    )
}

@Composable
fun DhcScoreText(
    badgeText: String?,
    score: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
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
            text = score,
            style = DhcTypoTokens.TitleH0.copy(brush = GradientColor.textGradient02),
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
