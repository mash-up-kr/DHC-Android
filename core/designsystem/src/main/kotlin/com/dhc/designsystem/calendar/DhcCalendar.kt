package com.dhc.designsystem.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate

@Composable
fun DhcCalendar(
    currentDate: LocalDate = LocalDate.now(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        DhcCalendarHeader(
            currentDate = currentDate,
            modifier = Modifier.fillMaxWidth(),
        )
        DhcCalendarWeekend(
            modifier = Modifier.fillMaxWidth()
        )
        DhcCalendarDate(
            date = currentDate,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DhcCalendarPreview() {
    DhcCalendar()
}
