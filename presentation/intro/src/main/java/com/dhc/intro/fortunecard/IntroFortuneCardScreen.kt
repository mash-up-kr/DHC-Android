package com.dhc.intro.fortunecard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.CalendarUtil
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.fortunecard.FlippableBox
import com.dhc.designsystem.score.DhcScoreText
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.component.WordBalloon
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroFortuneCardScreen(
    state: IntroFortuneCardContract.State,
    eventHandler: EventHandler<IntroFortuneCardContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_fortune_card_title),
                    titleStyle = DhcTypoTokens.TitleH2,
                ),
                textAlign = TextAlign.Center,
                subTitleState = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(
                modifier = Modifier
                    .height(height = if (state.isCardFlipped.not()) 506.dp else 85.dp),
            )
            if (state.isCardFlipped.not()) {
                NotFlippedDescription()
            } else {
                FlippedDescription()
            }
            Box(
                modifier = Modifier
                    .size(width = 319.dp, height = 280.dp)
                    .background(brush = GradientColor.backgroundGradient01)
                    .offset(y = -(10.dp)),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                FlippableBox(
                    isFlipped = state.isCardFlipped,
                    onFlipEnd = {
                        eventHandler(IntroFortuneCardContract.Event.FlippedFortuneCard)
                    },
                    frontScreen = {
                        // Todo : 디자인 확정되면 수정하기
                        DhcFortuneCard(
                            title = "?????",
                            description = "",
                            modifier = Modifier.width(width = 143.dp),
                        )
                    },
                    backScreen = {
                        // Todo : 디자인 확정되면 수정하기
                        DhcFortuneCard(
                            title = "오늘의 운세 카드",
                            description = "'한템포 쉬어가기'",
                            modifier = Modifier.width(width = 143.dp),
                        )
                    },
                    modifier = Modifier.align(Alignment.Center),
                    initialRotationZ = -4f,
                )
            }
        }
        if (state.isCardFlipped) {
            DhcButton(
                text = stringResource(R.string.start_with_finance_luck),
                buttonSize = DhcButtonSize.XLARGE,
                buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
                onClick = { eventHandler(IntroFortuneCardContract.Event.ClickNextButton) },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun NotFlippedDescription() {
    val colors = LocalDhcColors.current
    Text(
        text = stringResource(R.string.intro_fortune_card_description),
        style = DhcTypoTokens.TitleH3,
        color = colors.text.textBodyPrimary,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(48.dp))
    WordBalloon(
        gradientStartColor = Color(0xFFCFD4DE),
        gradientEndColor = Color(0xFF9BA4D5),
    ) {
        Text(
            text = stringResource(R.string.balloon_message_click),
            style = DhcTypoTokens.TitleH7,
            color = colors.text.textHighLightsPrimary,
        )
    }
}

@Composable
private fun FlippedDescription() {
    DhcScoreText(
        badgeText = CalendarUtil.getCurrentDate().run {
            stringResource(R.string.m_month_d_day_finance_luck, month.value, dayOfMonth)
        },
        score = 35,
        description = "마음이 들뜨는 날이에요,\n한템포 쉬어가요.",
    )
}

@Preview(showBackground = true)
@Composable
private fun IntroFortuneCardScreenPreview() {
    DhcTheme {
        IntroFortuneCardScreen(
            state = IntroFortuneCardContract.State(),
            eventHandler = {},
        )
    }
}
