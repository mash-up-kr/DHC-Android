package com.dhc.missionstatus.utils

import com.dhc.dhcandroid.model.Gender as  ServerGender
import com.dhc.dhcandroid.model.Generation as ServerGeneration

/**
 * 서버에서 돈이 소수점 형식, String 타입으로 내려오는데 '원' 단위, Int 타입으로 변환 해주는 함수
 */
fun String.toIntOrZero(): Int = toIntOrNull() ?: 0

fun ServerGeneration.getGenerationString(): String {
    return when (this) {
        ServerGeneration.TEENAGERS -> "10대"
        ServerGeneration.TWENTIES -> "20대"
        ServerGeneration.THIRTIES -> "30대"
        ServerGeneration.FORTIES -> "40대"
        ServerGeneration.UNKNOWN -> ""
    }
}

fun ServerGender.getGenderString(): String {
    return when (this) {
        ServerGender.MALE -> "남자"
        ServerGender.FEMALE -> "여자"
        ServerGender.UNKNOWN -> ""
    }
}