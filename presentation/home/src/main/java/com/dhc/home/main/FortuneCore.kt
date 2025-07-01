package com.dhc.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.GradientColor.fortuneGradientLow
import com.dhc.designsystem.GradientColor.fortuneGradientMid
import com.dhc.designsystem.GradientColor.fortuneGradientTop
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

@Composable
fun FortuneCore(
    fortuneScore: Int,
    modifier: Modifier = Modifier,
) {

    val textGradientColor = when (fortuneScore) {
        in 0 until 41 -> fortuneGradientLow
        in 41 until 71 -> fortuneGradientMid
        in 71..100 -> fortuneGradientTop
        else -> fortuneGradientMid
    }
    Column(
        modifier = modifier
            .background(shape = CircleShape, color = Color.Transparent)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(AccentColor.violet400.copy(alpha = 0.28f), SurfaceColor.neutral200.copy(alpha = 0.28f))),
                shape = CircleShape
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.money_fortune),
            style = DhcTypoTokens.Body6,
            color = SurfaceColor.neutral300,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.d_score, fortuneScore),
            style = DhcTypoTokens.TitleH3.copy(
                brush = GradientColor.textGradient01
            ),
            textAlign = TextAlign.Center,
        )

    }
}

@Preview
@Composable
fun PreviewFortuneCore() {
    DhcTheme {
        FortuneCore(
            fortuneScore = 35,
            modifier = Modifier.size(80.dp)
        )
    }
}