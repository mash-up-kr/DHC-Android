package com.dhc.designsystem.tipcard

import com.dhc.common.ImageResource

/**
 * 임시 UI모델로 서버 리스펀스에 따라 추후 변경
 */
data class TipCardModel(
    val title: String,
    val cont: String,
    val icon: ImageResource?,
    val color: String?
)
