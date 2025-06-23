package com.dhc.mypage.ui.settinglist

import androidx.annotation.DrawableRes

internal sealed interface SettingItem {
    val text: String
    @get:DrawableRes
    val iconRes: Int

    data class Normal(
        override val text: String,
        @DrawableRes override val iconRes: Int,
        val onClick: () -> Unit = {},
    ): SettingItem

    data class Toggle(
        override val text: String,
        @DrawableRes override val iconRes: Int,
        val isOn: Boolean,
        val onCheckedChange: (Boolean) -> Unit = {},
    ): SettingItem
}
