package com.dhc.mypage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dhc.common.FullRoundedCornerShape
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors

@Composable
fun DateAndTimeOfBirthInfo(modifier: Modifier = Modifier) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier
            .background(
                color = colors.background.backgroundGlassEffect,
                shape = FullRoundedCornerShape,
            )
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text (
            text = "2000년 1월 1일",
            textAlign = TextAlign.End,
            style = DhcTypoTokens.Body6,
            color = colors.text.textBodyPrimary,
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(width = 1.dp, height = 12.dp)
                .background(colors.background.backgroundGlassEffect),
        )
        Text (
            text = "오후 1시 30분",
            textAlign = TextAlign.Start,
            style = DhcTypoTokens.Body6,
            color = colors.text.textBodyPrimary,
        )
    }
}

@Preview
@Composable
private fun DateAndTimeOfBirthInfoPreview() {
    DhcTheme {
        DateAndTimeOfBirthInfo()
    }
}
