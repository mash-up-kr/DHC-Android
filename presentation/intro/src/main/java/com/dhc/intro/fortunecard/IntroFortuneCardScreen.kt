package com.dhc.intro.fortunecard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.graphicsLayer
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
import com.dhc.presentation.component.FortuneCardBack
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
            Spacer(modifier = Modifier.height(height = 44.dp))
            if (state.isCardFlipped.not()) {
                NotFlippedDescription()
            } else {
                FlippedDescription()
            }

            FlippableBox(
                isFlipped = state.isCardFlipped,
                onFlipEnd = {
                    eventHandler(IntroFortuneCardContract.Event.FlippedFortuneCard)
                },
                frontScreen = {
                    FortuneCardBack()
                },
                backScreen = {
                    DhcFortuneCard(
                        title = "최고의 날",
                        description = "네잎클로버",
                        cardDrawableResId = R.drawable.fortune_card_sample,
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
                    alpha = if (state.isCardFlipped) 0.4f else 1f,
                )
            }

        }
        if (state.isCardFlipped) {
            DhcButton(
                text = stringResource(R.string.intro_fortune_card_button),
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
    DhcScoreText(
        badgeText = null,
        score = stringResource(R.string.question_score),
        description = stringResource(R.string.intro_fortune_card_description),
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
    DhcScoreText(
        badgeText = null,
        score = 85,
        description = stringResource(R.string.intro_flipped_fortune_card_description),
        isDefaultColor = true
    )
    Spacer(modifier = Modifier.height(64.dp))
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
