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
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.borderGradient01
import com.dhc.designsystem.GradientColor.textGradient02
import com.dhc.mypage.R

@Composable
internal fun SajuCard(modifier: Modifier = Modifier) {
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
        // Todo :: 추후 이미지로 변경
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(color = SurfaceColor.neutral30),
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.my_saju),
            style = DhcTypoTokens.Body6,
            color = SurfaceColor.neutral200,
        )
        Text(
            text = "가을의 흰말",
            style = DhcTypoTokens.TitleH2.copy(brush = textGradient02),
        )
    }
}

@Preview
@Composable
private fun SajuCardPreview() {
    DhcTheme {
        SajuCard(
            modifier = Modifier.size(width = 180.dp, height = 120.dp)
        )
    }
}
