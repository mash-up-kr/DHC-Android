package com.dhc.mypage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.borderGradient01
import com.dhc.designsystem.GradientColor.textGradient02
import com.dhc.designsystem.SurfaceColor
import com.dhc.mypage.R

@Composable
internal fun SajuCard(
    sajuName: String,
    animalImageUrl: String?,
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
            .padding(vertical = 14.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = animalImageUrl,
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
            text = sajuName,
            style = DhcTypoTokens.TitleH2.copy(brush = textGradient02),
        )
    }
}

@Preview
@Composable
private fun SajuCardPreview() {
    DhcTheme {
        SajuCard(
            sajuName = "가을의 흰말",
            animalImageUrl = "",
            modifier = Modifier.size(width = 180.dp, height = 120.dp),
        )
    }
}
