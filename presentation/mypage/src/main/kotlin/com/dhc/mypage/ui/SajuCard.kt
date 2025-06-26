package com.dhc.mypage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.SurfaceColor
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.borderGradient01
import com.dhc.designsystem.GradientColor.textGradient02
import com.dhc.dhcandroid.model.AnimalCard
import com.dhc.mypage.R

@Composable
internal fun SajuCard(
    sajuCard: AnimalCard,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = SurfaceColor.neutral900.copy(alpha = 0.6f),
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                width = 1.dp,
                brush = borderGradient01,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = sajuCard.cardImageUrl,
            contentDescription = "saju card image",
            modifier = Modifier.size(36.dp)
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.my_saju),
            style = DhcTypoTokens.Body6,
            color = SurfaceColor.neutral200,
        )
        Text(
            text = sajuCard.name,
            style = DhcTypoTokens.TitleH2.copy(brush = textGradient02),
        )
    }
}

@Preview
@Composable
private fun SajuCardPreview() {
    DhcTheme {
        SajuCard(
            sajuCard = AnimalCard(
                name = "가을의 흰말",
                cardImageUrl = ""
            ),
            modifier = Modifier.size(width = 180.dp, height = 120.dp)
        )
    }
}
