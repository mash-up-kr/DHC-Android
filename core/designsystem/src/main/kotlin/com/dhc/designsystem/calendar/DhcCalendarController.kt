package com.dhc.designsystem.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.dhc.designsystem.calendar.model.DhcCalendarInitialData
import com.dhc.designsystem.calendar.model.DhcCalendarMonthData
import java.time.LocalDate

class DhcCalendarController(
    private val initialData: DhcCalendarInitialData,
) {
    var currentDate by mutableStateOf(initialData.initialDate)
        private set

    var calendarMonthState by mutableStateOf(mapOf<LocalDate, DhcCalendarMonthData>())
        private set

    fun getDateByPage(page: Int): LocalDate =
        initialData.initialDate.plusMonths(page.toLong() - initialData.initialPage).withDayOfMonth(1)

    fun onChangePage(page: Int) {
        currentDate = getDateByPage(page)
    }

    companion object {
        val Saver: Saver<DhcCalendarController, String> = Saver(
            save = { it.initialData.initialDate.toString() },
            restore = {
                DhcCalendarController(
                    DhcCalendarInitialData(LocalDate.parse(it))
                )
            },
        )
    }
}

@Composable
fun rememberDhcCalendarController(
    initialDate: LocalDate = LocalDate.now(),
): DhcCalendarController {
    return rememberSaveable(saver = DhcCalendarController.Saver) {
        DhcCalendarController(
            initialData = DhcCalendarInitialData(
                initialDate = initialDate,
            )
        )
    }
}
