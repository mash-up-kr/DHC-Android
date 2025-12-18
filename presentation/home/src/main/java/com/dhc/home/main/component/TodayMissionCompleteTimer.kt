package com.dhc.home.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import kotlinx.coroutines.delay

@Composable
fun TodayMissionCompleteTimer(
    modifier: Modifier = Modifier,
    remainingTimeMillis: Long = 0L,
) {
    val colors = LocalDhcColors.current

    var currentTimeMillis by remember { mutableLongStateOf(remainingTimeMillis) }

    LaunchedEffect(remainingTimeMillis) {
        currentTimeMillis = remainingTimeMillis
        while (currentTimeMillis > 0) {
            delay(1000L)
            currentTimeMillis -= 1000L
        }
    }

    val hours = (currentTimeMillis / (1000 * 60 * 60)) % 24
    val minutes = (currentTimeMillis / (1000 * 60)) % 60
    val seconds = (currentTimeMillis / 1000) % 60

    val timerText = String.format("%02d : %02d : %02d 남음", hours, minutes, seconds)

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
            text = timerText,
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
            remainingTimeMillis = 72530000L, // 20:08:50
        )
    }
}
