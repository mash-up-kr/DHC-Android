package com.dhc.intro.start

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.component.VideoView
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroStartScreen(
    eventHandler: EventHandler<IntroStartContract.Event>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    val scrollState = rememberScrollState()
    Box(modifier = modifier) {
        VideoView(
            videoResId = R.raw.intro_start_video,
            thumbnailResId = R.drawable.intro_start_video_thumbnail,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .aspectRatio(375f / 672f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_start_title),
                    titleStyle = DhcTypoTokens.TitleH2_1,
                ),
                textAlign = TextAlign.Center,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_start_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
        }
        Text(
            text = stringResource(R.string.next),
            color = colors.text.textMain,
            style = DhcTypoTokens.TitleH5_1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = SurfaceColor.neutral700,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { eventHandler(IntroStartContract.Event.ClickNextButton) }
                .padding(vertical = 15.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroStartScreenPreview() {
    DhcTheme {
        IntroStartScreen(
            eventHandler = {},
            modifier = Modifier,
        )
    }
}
