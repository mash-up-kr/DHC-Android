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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.fortuneFillGradient
import com.dhc.designsystem.GradientColor.fortuneGradientLow
import com.dhc.designsystem.GradientColor.fortuneGradientMid
import com.dhc.designsystem.GradientColor.fortuneGradientTop
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.score.toGradientScoreColor

@Composable
fun FortuneCore(
    fortuneScore: Int,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .background(shape = CircleShape, color = Color.Transparent)
            .border(
                width = 1.dp,
                brush = fortuneFillGradient,
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
                brush = fortuneScore.toGradientScoreColor()
            ),
            textAlign = TextAlign.Center,
        )

    }
}

@Preview
@Composable
private fun PreviewFortuneCore(
    @PreviewParameter(PreviewFortuneCoreProvider::class)
    parameter: PreviewFortuneCoreProvider.Parameter,
) {
    DhcTheme {
        FortuneCore(
            fortuneScore = parameter.fortuneScore,
            modifier = Modifier.size(80.dp)
        )
    }
}

private class PreviewFortuneCoreProvider :
    PreviewParameterProvider<PreviewFortuneCoreProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            fortuneScore = 35
        ),
        Parameter(
            fortuneScore = 50
        ),
        Parameter(
            fortuneScore = 80
        ),

        )

    data class Parameter(
        val fortuneScore: Int
    )
}