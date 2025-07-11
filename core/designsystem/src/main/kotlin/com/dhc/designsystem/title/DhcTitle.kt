package com.dhc.designsystem.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcTitle(
    titleState: DhcTitleState,
    textAlign: TextAlign,
    modifier: Modifier = Modifier,
    subTitleState: DhcTitleState? = null,
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = titleState.title,
            style = titleState.titleStyle,
            color = colors.text.textMain,
            textAlign = textAlign,
            modifier = Modifier.fillMaxWidth(),
        )
        subTitleState?.let { titleState ->
            Text(
                text = titleState.title,
                style = titleState.titleStyle,
                color = SurfaceColor.neutral300,
                textAlign = textAlign,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

private class DhcTitlePreviewProvider : PreviewParameterProvider<DhcTitlePreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "매일매일 금전운 미션을 통해\n소비습관을 개선해보세요",
            textAlign = TextAlign.Center,
            subTitle = "매일매일 금전운 미션을 통해\n소비습관을 개선해보세요",
        ),
        Parameter(
            title = "매일매일 금전운 미션을 통해\n소비습관을 개선해보세요",
            textAlign = TextAlign.Center,
            subTitle = null,
        ),
        Parameter(
            title = "매일매일 금전운 미션을 통해\n소비습관을 개선해보세요",
            textAlign = TextAlign.Start,
            subTitle = "매일매일 금전운 미션을 통해\n소비습관을 개선해보세요",
        ),
    )

    data class Parameter(
        val title: String,
        val textAlign: TextAlign,
        val subTitle: String?
    )
}

@Preview
@Composable
private fun DhcTitlePreview(
    @PreviewParameter(DhcTitlePreviewProvider::class)
    parameter: DhcTitlePreviewProvider.Parameter,
) {
    DhcTheme {
        DhcTitle(
            titleState = DhcTitleState(
                title = parameter.title,
                titleStyle = DhcTypoTokens.TitleH2,
            ),
            textAlign = parameter.textAlign,
            subTitleState = parameter.subTitle?.let {
                DhcTitleState(
                    title = it,
                    titleStyle = DhcTypoTokens.Body3,
                )
            },
        )
    }
}
