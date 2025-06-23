package com.dhc.designsystem.calendar.model

import java.time.LocalDate

data class DhcCalendarMonthData(
    val yearMonth: LocalDate = LocalDate.now(),
    val data: Map<Int, DhcCalendarDayData> = emptyMap(),
)

data class DhcCalendarDayData(
    val date: LocalDate,
    val finishedMissionCount: Int,
)
