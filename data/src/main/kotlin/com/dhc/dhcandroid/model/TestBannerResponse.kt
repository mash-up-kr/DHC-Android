package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class TestBannerResponse(
    val version: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val imageUrl: String = "",
    val testUrl: String = "",
)