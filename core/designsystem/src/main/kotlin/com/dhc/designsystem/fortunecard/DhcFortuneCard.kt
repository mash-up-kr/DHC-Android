package com.dhc.designsystem.fortunecard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.DhcTypoTokens.typoFontFamily
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcFortuneCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = SurfaceColor.neutral700,
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AccentColor.violet300,
                            SurfaceColor.neutral600,
                        ),
                    ),
                ),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(vertical = 20.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            color = SurfaceColor.neutral200,
            style = DhcTypoTokens.Body7,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(119.dp))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = description,
            style = TextStyle(
                fontFamily = typoFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                brush = GradientColor.textGradient02,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

private class DhcFortuneCardPreviewProvider : PreviewParameterProvider<DhcFortuneCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "오늘의 운세 카드",
            description = "'한템포 쉬어가기'",
        ),
        Parameter(
            title = "???",
            description = "",
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
            modifier = Modifier.width(143.dp),
        )
    }
}
