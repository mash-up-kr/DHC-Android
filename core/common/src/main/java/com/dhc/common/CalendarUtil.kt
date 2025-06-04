package com.dhc.common

import java.util.Calendar

fun Calendar.getCurrentYear() = get(Calendar.YEAR)

fun Calendar.getActualMaximumDayOfMonth(currentYear: Int, currentMonth: Int): Int =
    this.apply { set(currentYear, currentMonth-1, 1) }.getActualMaximum(Calendar.DAY_OF_MONTH)
