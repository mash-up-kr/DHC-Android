package com.dhc.missionstatus.utils

import com.dhc.dhcandroid.model.Gender as  ServerGender

/**
 * 서버에서 돈이 소수점 형식, String 타입으로 내려오는데 '원' 단위, Int 타입으로 변환 해주는 함수
 */
fun String.toIntOrZero(): Int = toIntOrNull() ?: 0

fun ServerGender.getGenderString(): String {
    return when (this) {
        ServerGender.UNKNOWN -> ""
        ServerGender.MALE -> "남성"
        ServerGender.FEMALE -> "여성"
        ServerGender.UNKNOWN -> ""
    }
}
