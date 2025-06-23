package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import com.dhc.designsystem.calendar.model.DhcCalendarInitialData
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
        DhcCalendarDate(day = controller.getDateByPage(page))
    }
}

@Composable
fun DhcCalendarDate(
    day: LocalDate,
    modifier: Modifier = Modifier,
) {
    val days by remember(day) { mutableStateOf(getDaysOfMonth(day)) }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(DayOfWeek.entries.size),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(days.size) { index ->
            // Todo :: 날짜별 다른 디자인 적용
            DhcCalendarDay(
                day = days[index].dayOfMonth,
                modifier = Modifier.height(36.dp),
            )
        }
    }
}

@Preview
@Composable
private fun DhcCalendarDateSwiperPreview() {
    DhcTheme {
        DhcCalendarDateSwiper(
            pagerState = rememberPagerState(
                initialPage = 1,
                initialPageOffsetFraction = 0f,
                pageCount = { 1 },
            ),
            controller = DhcCalendarController(DhcCalendarInitialData())
        )
    }
}
