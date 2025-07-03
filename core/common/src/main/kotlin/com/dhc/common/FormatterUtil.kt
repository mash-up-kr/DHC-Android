package com.dhc.common

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.math.abs

object FormatterUtil {
    val dhcDateFormat = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
    val dhcTimeFormat = DateTimeFormatter.ofPattern("a h시 m분", Locale.KOREAN)
    val dhcYearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM")
    val dhcYearMonthDayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val wonFormat = NumberFormat.getNumberInstance(Locale.KOREA)
    val todayStringFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun String.toDDay(date: LocalDate = LocalDate.now()): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val targetDate = LocalDate.parse(this, formatter)
            val daysBetween = ChronoUnit.DAYS.between(date, targetDate)

            when {
                daysBetween > 0 -> "D-${daysBetween}"
                daysBetween == 0L -> "D-Day"
                else -> "D+${abs(daysBetween)}"
            }
        } catch (e: Exception) {
            "Invalid Date"
        }
    }
}
