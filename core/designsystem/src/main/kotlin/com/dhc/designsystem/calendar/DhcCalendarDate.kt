package com.dhc.designsystem.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.calendar.CalendarUtils.getDaysOfMonth
import java.time.LocalDate

@Composable
fun DhcCalendarDateSwiper(
    date: LocalDate,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE },
    ),
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) {
        DhcCalendarDate(
            date = date,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun DhcCalendarDate(
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    val days by remember(date) { mutableStateOf(getDaysOfMonth(date)) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(days.size) { index ->
            DhcCalendarDay(
                day = days[index].dayOfMonth,
                modifier = Modifier
                    .aspectRatio(1f)
                    .size(36.dp)
            )
        }
    }
}

@Composable
fun DhcCalendarDay(
    day: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.toString(),
            color = Color.White,
        )
    }
}

@Preview
@Composable
private fun DhcCalendarDateSwiperPreview() {
    DhcTheme {
        DhcCalendarDateSwiper(
            date = LocalDate.now(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
