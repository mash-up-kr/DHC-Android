package com.dhc.designsystem.fortunecard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhc.common.FullRoundedCornerShape
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.DhcTypoTokens.typoFontFamily
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.colors

@Composable
fun DhcFortuneCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(
                color = SurfaceColor.neutral700,
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    brush = GradientColor.cardBorderGradient01,
                ),
                shape = RoundedCornerShape(12.dp),
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(vertical = 20.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = DhcTypoTokens.TitleH8.copy(brush = GradientColor.textGradient02),
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = description,
            style = DhcTypoTokens.TitleH6,
            textAlign = TextAlign.Center,
            color = colors.text.textBodyPrimary,
        )
    }
}

private class DhcFortuneCardPreviewProvider : PreviewParameterProvider<DhcFortuneCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "최고의 날",
            description = "네잎클로버",
        ),
        Parameter(
            title = "카드 뒷면",
            description = "임시",
        ),
    )

    data class Parameter(
        val title: String,
        val description: String,
    )
}

@Preview
@Composable
private fun DhcFortuneCardPreview(
    @PreviewParameter(DhcFortuneCardPreviewProvider::class)
    parameter: DhcFortuneCardPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcFortuneCard(
            title = parameter.title,
            description = parameter.description,
            modifier = Modifier.size(143.dp, 197.dp),
        )
    }
}
