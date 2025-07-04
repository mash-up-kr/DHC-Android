package com.dhc.designsystem.calendar.model

import java.time.LocalDate

data class DhcCalendarInitialData(
    val initialDate: LocalDate = LocalDate.now(),
    val initialPage: Int = Int.MAX_VALUE / 2,
)
