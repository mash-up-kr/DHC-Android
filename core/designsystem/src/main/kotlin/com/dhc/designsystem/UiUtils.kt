package com.dhc.designsystem

import android.content.Context
import coil3.ImageLoader
import coil3.svg.SvgDecoder

fun Context.getImageLoader() = ImageLoader.Builder(this)
    .components {
        add(SvgDecoder.Factory())
    }
    .build()
