package com.dhc.home.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.fortunecard.DhcFortuneCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = "5월 20일",
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral300,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MonetaryLuckInfo()
        Spacer(modifier = Modifier.height(12.dp))
        DhcFortuneCard(
            title = "오늘의 운세 카드",
            description = "한템포 쉬어가기",
            modifier = Modifier
                .width(143.dp)
                .padding(top = 20.dp, bottom = 53.5.dp)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(12.dp))
        SpendingHabitMission()
        Spacer(modifier = Modifier.height(24.dp))
        MonetaryLuckyDailyMission()
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    DhcTheme {
        HomeScreen()
    }
}