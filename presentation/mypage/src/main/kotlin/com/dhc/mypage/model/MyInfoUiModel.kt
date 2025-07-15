package com.dhc.mypage.model

import com.dhc.dhcandroid.model.MyPageResponse
import java.time.LocalDate
import java.time.LocalTime

data class MyInfoUiModel(
    val sajuName: String = "",
    val animalImageUrl: String? = null,
    val birthDate: LocalDate? = null,
    val birthTime: LocalTime? = null,
) {
    companion object {
        fun from(myPageResponse: MyPageResponse) = MyInfoUiModel(
            sajuName = myPageResponse.animalCard.name,
            animalImageUrl = myPageResponse.animalCard.cardImage,
            birthDate = myPageResponse.birthDate?.let { runCatching { LocalDate.parse(it.date) }.getOrNull() },
            birthTime = myPageResponse.birthTime?.let { runCatching { LocalTime.parse(it) }.getOrNull() },
        )
    }
}
