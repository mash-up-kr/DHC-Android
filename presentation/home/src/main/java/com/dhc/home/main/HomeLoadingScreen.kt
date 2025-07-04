package com.dhc.home.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.CalendarUtil
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.home.R
import com.dhc.presentation.component.TopGradiantBox
import com.dhc.presentation.component.VideoView

@Composable
fun HomeLoadingScreen(modifier: Modifier = Modifier) {
    val colors = LocalDhcColors.current
    val density = LocalDensity.current
    val topBarSize = WindowInsets.statusBars.getTop(density)

    TopGradiantBox(modifier = modifier.fillMaxSize()) {
        VideoView(
            videoResId = R.raw.loading_video,
            thumbnailResId = R.drawable.loading_video_thumbnail,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -(topBarSize.div(density.density).dp)),
        )

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(84.dp))
            Text(
                text = CalendarUtil.getCurrentDate()
                    .let { stringResource(R.string.m_month_d_day, it.month.value, it.dayOfMonth) },
                color = SurfaceColor.neutral300,
                style = DhcTypoTokens.Body3,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.loading_today_fortune_card),
                color = colors.text.textBodyPrimary,
                style = DhcTypoTokens.TitleH4,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
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
