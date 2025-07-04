package com.dhc.common

import androidx.annotation.DrawableRes

sealed interface ImageResource {
    data class Drawable(@DrawableRes val resId: Int) : ImageResource
    data class Url(val url: String) : ImageResource
}
