package com.dhc.designsystem.gnb.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GnbItem(
    @DrawableRes val iconResource: Int,
    @StringRes val iconText: Int,
    val routeName: String,
)
