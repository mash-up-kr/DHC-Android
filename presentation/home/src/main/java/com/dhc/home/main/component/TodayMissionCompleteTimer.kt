package com.dhc.home.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors

@Composable
fun TodayMissionCompleteTimer(
    modifier: Modifier = Modifier,
    timerText: String = "00 : 00 : 00 남음",
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "오늘 미션 종료까지",
            style = DhcTypoTokens.Body4,
            color = colors.text.textBodyPrimary.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "$timerText 남음",
            style = DhcTypoTokens.TitleH2_1,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun TodayMissionCompleteTimerPreview() {
    DhcTheme {
        TodayMissionCompleteTimer(
            timerText = "20 : 08 : 50 남음"
        )
    }
}
