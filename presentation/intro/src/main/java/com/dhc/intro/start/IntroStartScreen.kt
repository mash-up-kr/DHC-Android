package com.dhc.intro.start

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroStartScreen(
    eventHandler: EventHandler<IntroStartContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        VideoView(
            videoResId = R.raw.intro_video,
            thumbnailResId = R.drawable.intro_thumbnail, // Todo : 섬네일 넣어줄 예정
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(24.dp))
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
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(38.dp))
        }
        DhcButton(
            text = stringResource(R.string.start_with_finance_luck),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
            onClick = { eventHandler(IntroStartContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun VideoView(
    @RawRes videoResId: Int,
    @DrawableRes thumbnailResId: Int,
    modifier: Modifier = Modifier,
) {
    val localContext = LocalContext.current
    var onPlayWhenReady by remember { mutableStateOf(false) }
    val exoPlayer = remember {
        ExoPlayer.Builder(localContext).build().apply {
            setMediaItem(
                MediaItem.fromUri("android.resource://${localContext.packageName}/${videoResId}")
            )
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
            playWhenReady = true
        }
    }

    LifecycleStartEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                onPlayWhenReady = playbackState == Player.STATE_READY
            }
        }
        exoPlayer.addListener(listener)
        onStopOrDispose {
            exoPlayer.release()
            exoPlayer.removeListener(listener)
        }
    }

    Box(modifier = modifier) {
        AnimatedVisibility(onPlayWhenReady.not()) {
            Image(
                painter = painterResource(thumbnailResId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    useController = false
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { playerView ->
                playerView.visibility = if (onPlayWhenReady) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroStartScreenPreview() {
    DhcTheme {
        IntroStartScreen(eventHandler = {})
    }
}
