package com.dhc.designsystem.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.SurfaceColor.neutral700
import java.time.LocalDate

@Composable
fun DhcCalendar(
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now(),
) {
    Column(
        modifier = modifier.background(color = neutral700),
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
    DhcTheme {
        DhcCalendar()
    }
}
