package com.dhc.presentation.component

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoView(
    @RawRes videoResId: Int,
    @DrawableRes thumbnailResId: Int?,
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
        if (thumbnailResId != null) {
            AnimatedVisibility(onPlayWhenReady.not()) {
                Image(
                    painter = painterResource(thumbnailResId),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
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
