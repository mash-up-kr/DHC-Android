package com.dhc.home.model

import com.dhc.dhcandroid.model.TestBannerResponse

data class TestBannerUiModel(
    val title: String = "",
    val subTitle: String = "",
    val imageUrl: String = "",
    val testUrl: String = "",
) {
    companion object {
        fun from(model: TestBannerResponse): TestBannerUiModel = TestBannerUiModel(
            title = model.title,
            subTitle = model.subTitle,
            imageUrl = model.imageUrl,
            testUrl = model.testUrl,
        )
    }
}