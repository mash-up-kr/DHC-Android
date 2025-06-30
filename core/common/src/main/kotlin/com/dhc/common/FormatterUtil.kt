package com.dhc.common

import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

object FormatterUtil {
    val dhcDateFormat = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
    val dhcTimeFormat = DateTimeFormatter.ofPattern("a h시 m분", Locale.KOREAN)
    val wonFormat = NumberFormat.getNumberInstance(Locale.KOREA)
}