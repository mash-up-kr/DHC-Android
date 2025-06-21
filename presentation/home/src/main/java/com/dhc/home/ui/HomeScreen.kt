package com.dhc.home.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.floatingButton.DhcFloatingButton
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.fortunecard.FlippableBox
import com.dhc.home.R
import com.dhc.presentation.component.TopGradientBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        TopGradientBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(466.dp)
                .offset(y = -(54.dp))
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
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
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(width = 319.dp, height = 280.dp)
                    .background(brush = GradientColor.backgroundGradient01)
                    .offset(y = -(10.dp)),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                FlippableBox(
                    isFlipped = false,
                    onFlipEnd = {},
                    frontScreen = {
                        DhcFortuneCard(
                            title = "오늘의 운세 카드",
                            description = "한템포 쉬어가기",
                            modifier = Modifier
                                .width(143.dp)
                                .padding(top = 20.dp, bottom = 53.5.dp)
                                .align(Alignment.Center),
                        )
                    },
                    backScreen = {
                        DhcFortuneCard(
                            title = "오늘의 운세 카드",
                            description = "한템포 쉬어가기",
                            modifier = Modifier
                                .width(143.dp)
                                .padding(top = 20.dp, bottom = 53.5.dp)
                                .align(Alignment.Center),
                        )
                    },
                    modifier = Modifier.align(Alignment.Center),
                    initialRotationZ = -4f,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            SpendingHabitMission()
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckyDailyMission()
        }

        DhcFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 20.dp),
            text = stringResource(R.string.finish_today_mission),
            isEnabled = true,
            onClick = {},
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    DhcTheme {
        HomeScreen()
    }
}
