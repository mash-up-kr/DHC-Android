package com.dhc.designsystem.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens.Body3
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.calendar.CalendarUtils.getDayOfWeekInKorean
import java.time.DayOfWeek

@Composable
fun DhcCalendarWeekend(
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(7) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = getDayOfWeekInKorean(DayOfWeek.entries[index]),
                    color = colors.text.textMain,
                    style = Body3,
                )
            }
        }
    }
}


@Preview
@Composable
private fun DhcCalendarWeekendPreview() {
    DhcTheme {
        DhcCalendarWeekend(Modifier.fillMaxWidth())
    }
}
