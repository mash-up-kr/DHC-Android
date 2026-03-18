package com.dhc.mypage.ui.settinglist

import com.dhc.common.ImageResource

internal sealed interface SettingItem {
    val text: String
    val imageResource: ImageResource

    data class Normal(
        override val text: String,
        override val imageResource: ImageResource,
        val isArrowVisible: Boolean,
        val onClick: () -> Unit = {},
    ): SettingItem

    data class Toggle(
        override val text: String,
        override val imageResource: ImageResource,
        val isOn: Boolean,
        val onCheckedChange: (Boolean) -> Unit = {},
    ): SettingItem
}
