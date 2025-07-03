package com.dhc.designsystem

import android.content.Context
import coil3.ImageLoader
import coil3.svg.SvgDecoder

fun Context.getSvgImageLoader() = ImageLoader.Builder(this)
    .components {
        add(SvgDecoder.Factory())
    }.build()
