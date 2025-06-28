package com.dhc.mypage.utils

import java.time.format.DateTimeFormatter
import java.util.Locale

internal object Formatter {
    val sajuCardDateFormat = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
    val sajuCardTimeFormat = DateTimeFormatter.ofPattern("a h시 m분", Locale.KOREAN)
}
