package com.dhc.common

import java.time.LocalDate
import java.time.Year

object CalendarUtil {

    fun getCurrentYear(): Int = Year.now().value

    fun getActualMaximumDayOfMonth(year: Int, month: Int): Int =
        LocalDate.of(year, month, 1).lengthOfMonth()
}
