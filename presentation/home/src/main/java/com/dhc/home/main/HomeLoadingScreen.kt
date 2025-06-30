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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.fortunecard.FlippableBox
import com.dhc.home.R

@Composable
fun HomeLoadingScreen(modifier: Modifier = Modifier) {
    val colors = LocalDhcColors.current
    val density = LocalDensity.current
    val topBarSize = WindowInsets.statusBars.getTop(density)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(466.dp)
            .offset(y = -(topBarSize.div(density.density).dp))
            .background(brush = GradientColor.backgroundGradient02Alpha(0.6f))
    )
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.dp))
        Text(
            text = "오늘의 운세를 카드에 담고 있어요..",
            color = colors.text.textBodyPrimary,
            style = DhcTypoTokens.TitleH4,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "5월 21일",
            color = SurfaceColor.neutral300,
            style = DhcTypoTokens.Body3,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(68.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            // Todo : 로띠 애니메이션이 들어갈 예정
            val canvasBackgroundBrush = GradientColor.backgroundGradient01(radius = 350f)
            Canvas(modifier = Modifier.matchParentSize()) {
                drawOval(
                    brush = canvasBackgroundBrush,
                    size = size,
                    alpha = 0.6f,
                )
            }
            FlippableBox(
                isFlipped = false,
                onFlipEnd = {},
                frontScreen = {
                    DhcFortuneCard(
                        title = "최고의 날",
                        description = "네잎클로버",
                        cardDrawableResId = R.drawable.fortune_card_sample,
                        modifier = Modifier.size(width = 143.dp, height = 197.dp),
                    )
                },
                backScreen = {
                    DhcFortuneCard(
                        title = "최고의 날",
                        description = "네잎클로버",
                        cardDrawableResId = R.drawable.fortune_card_sample,
                        modifier = Modifier.size(width = 143.dp, height = 197.dp),
                    )
                },
                modifier = Modifier.align(Alignment.Center),
                initialRotationZ = -4f,
                isEnabled = false,
            )
        }
    }
}

@Preview
@Composable
private fun HomeLoadingScreenPreview() {
    DhcTheme {
        HomeLoadingScreen(modifier = Modifier)
    }
}
