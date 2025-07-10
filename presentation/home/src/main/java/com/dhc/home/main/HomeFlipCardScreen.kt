package com.dhc.home.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.CalendarUtil
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.fortunecard.FlippableBox
import com.dhc.designsystem.score.DhcScoreText
import com.dhc.home.R
import com.dhc.home.model.TodayDailyFortuneUiModel
import com.dhc.designsystem.R as DR
import com.dhc.presentation.component.FortuneCardBack
import com.dhc.presentation.component.WordBalloon
import com.dhc.presentation.mvi.EventHandler

@Composable
fun HomeFlipCardScreen(
    todayFortune: TodayDailyFortuneUiModel,
    eventHandler: EventHandler<HomeContract.Event>,
    modifier: Modifier = Modifier,
) {
    var isCardFlipped by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.dp))
        if (isCardFlipped.not()) {
            NotFlippedDescription()
        } else {
            FlippedDescription()
        }

        FlippableBox(
            isFlipped = isCardFlipped,
            onFlipEnd = {
                isCardFlipped = true
                eventHandler(HomeContract.Event.FortuneCardFlipped)
            },
            frontScreen = {
                FortuneCardBack()
            },
            backScreen = {
                DhcFortuneCard(
                    title = "최고의 날",
                    description = "네잎클로버",
                    imageUrl = todayFortune.fortuneCardImage,
                )
            },
            initialRotationZ = -4f,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Canvas(
            modifier = Modifier
                .size(width = 32.dp, height = 32.dp)
                .graphicsLayer { scaleX = 4f },
        ) {
            drawOval(
                brush = GradientColor.cardBottomGradient01,
                size = size,
                alpha = 0.4f,
            )
        }
    }
}

@Composable
private fun NotFlippedDescription() {
    val colors = LocalDhcColors.current
    DhcScoreText(
        badgeText = CalendarUtil.getCurrentDate().run {
            "%d년 %d월 %d일".format(year, month.value, dayOfMonth) // Todo : 공통 Formmater 로 이동
        },
        score = stringResource(R.string.question_score),
        description = stringResource(R.string.home_fortune_card_description),
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(24.dp))
    WordBalloon(
        gradientStartColor = Color(0xFFCFD4DE),
        gradientEndColor = Color(0xFF9BA4D5),
    ) {
        Text(
            text = stringResource(R.string.balloon_message_flip),
            style = DhcTypoTokens.TitleH7,
            color = colors.text.textHighLightsPrimary,
        )
    }
    Spacer(modifier = Modifier.height(23.dp))
}

@Composable
private fun FlippedDescription() {
    val dateFormat = "%d년 %d월 %d일" // Todo : 공통 Formatter 로 이동
    DhcScoreText(
        badgeText = CalendarUtil.getCurrentDate().run {
            dateFormat.format(year, month.value, dayOfMonth)
        },
        score = 35,
        description = "마음이 들뜨는 날이에요,\n한템포 쉬어가요.",
    )
    Spacer(modifier = Modifier.height(64.dp))
}

@Preview
@Composable
private fun HomeFlipCardScreenPreview() {
    DhcTheme {
        HomeFlipCardScreen(
            todayFortune = TodayDailyFortuneUiModel(),
            eventHandler = {},
        )
    }
}
