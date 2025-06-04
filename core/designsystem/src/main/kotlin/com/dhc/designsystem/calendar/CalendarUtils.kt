package com.dhc.designsystem.calendar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

object CalendarUtils {

    fun getDayOfWeekInKorean(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.SATURDAY -> "토"
            DayOfWeek.SUNDAY -> "일"
        }
    }

    fun getDaysOfMonth(date: LocalDate): List<LocalDate> {
        val startOfMonth =
            date.withDayOfMonth(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        return (0..34L).map { startOfMonth.plusDays(it) }.apply {
            if (last() < date.withDayOfMonth(date.lengthOfMonth())) {
                this + (0..6L).map { startOfMonth.plusDays(35 + it) }
            }
        }
    }
}
