package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.calendar.CalendarUtils.getDaysOfMonth
import com.dhc.designsystem.calendar.DhcCalendarController
import com.dhc.designsystem.calendar.model.DhcCalendarDayData
import com.dhc.designsystem.calendar.model.DhcCalendarDayUiModel
import com.dhc.designsystem.calendar.model.DhcCalendarMonthData
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun DhcCalendarDateSwiper(
    pagerState: PagerState,
    controller: DhcCalendarController,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        modifier = modifier.height(256.dp),
        state = pagerState,
        pageSpacing = 8.dp,
    ) { page ->
        val date = controller.getDateByPage(page)
        DhcCalendarDate(
            day = date,
            monthData = controller.calendarMonthState[date]
                ?: DhcCalendarMonthData(yearMonth = date),
        )
    }
}

@Composable
fun DhcCalendarDate(
    day: LocalDate,
    modifier: Modifier = Modifier,
    monthData: DhcCalendarMonthData = DhcCalendarMonthData(),
) {
    val days by remember(day) { mutableStateOf(getDaysOfMonth(day)) }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(DayOfWeek.entries.size),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(days.size) { index ->
            DhcCalendarDay(
                day = days[index].dayOfMonth,
                modifier = Modifier.height(36.dp),
                uiModel = DhcCalendarDayUiModel.from(
                    day = days[index],
                    monthData = monthData,
                )
            )
        }
    }
}

@Preview
@Composable
private fun DhcCalendarDatePreview() {
    DhcTheme {
        DhcCalendarDate(
            day = LocalDate.now(),
            monthData = DhcCalendarMonthData(
                yearMonth = LocalDate.now(),
                data = mapOf(
                    1 to DhcCalendarDayData(0),
                    2 to DhcCalendarDayData(1),
                    3 to DhcCalendarDayData(2),
                    4 to DhcCalendarDayData(3),
                )
            )
        )
    }
}
