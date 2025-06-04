package com.dhc.designsystem.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DHCAndroidTheme
import com.dhc.designsystem.calendar.CalendarUtils.getDayOfWeekInKorean
import java.time.DayOfWeek

@Composable
fun DhcCalendarWeekend(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DayOfWeek.entries.forEach {
            Text(getDayOfWeekInKorean(it))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DhcCalendarWeekendPreview() {
    DHCAndroidTheme {
        DhcCalendarWeekend(modifier = Modifier.fillMaxWidth())
    }
}
