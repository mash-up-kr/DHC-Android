package com.dhc.mypage.model

import com.dhc.dhcandroid.model.MyPageResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class MyInfoUiModel(
    val sajuName: String = "",
    val animalImageUrl: String? = null,
    val birthDateTime: LocalDateTime? = null,
) {
    companion object {
        fun from(myPageResponse: MyPageResponse) = MyInfoUiModel(
            sajuName = myPageResponse.animalCard.name,
            animalImageUrl = myPageResponse.animalCard.cardImage,
            birthDateTime = LocalDateTime.of(
                LocalDate.parse(myPageResponse.birthDate?.date),
                LocalTime.parse(myPageResponse.birthTime)
            )
        )
    }
}
