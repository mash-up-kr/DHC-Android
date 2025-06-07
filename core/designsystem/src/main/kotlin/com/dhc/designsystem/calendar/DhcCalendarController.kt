package com.dhc.designsystem.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import java.time.LocalDate

@Stable
class DhcCalendarController(
    private val initialDate: LocalDate = LocalDate.now(),
    private val initialPage: Int = Int.MAX_VALUE / 2,
) {
    val currentDate = mutableStateOf(initialDate)

    fun getDate(page: Int) = initialDate.plusDays(page.toLong() - initialPage)

    fun onChangePage(page: Int) {
        currentDate.value = initialDate.plusDays(page.toLong()- initialPage)
    }
}

@Composable
fun rememberDhcCalendarController(
    initialDate: LocalDate = LocalDate.now(),
): DhcCalendarController {
    return rememberSaveable {
        DhcCalendarController(initialDate)
    }
}
