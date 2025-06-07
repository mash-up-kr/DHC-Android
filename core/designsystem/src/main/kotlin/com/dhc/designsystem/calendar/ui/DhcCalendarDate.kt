package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens.Body3
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.calendar.CalendarUtils.getDaysOfMonth
import com.dhc.designsystem.calendar.DhcCalendarController
import java.time.LocalDate

@Composable
fun DhcCalendarDateSwiper(
    pagerState: PagerState,
    controller: DhcCalendarController,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) { page ->
        DhcCalendarDate(day = controller.getDate(page))
    }
}

@Composable
fun DhcCalendarDate(
    day: LocalDate,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val days = getDaysOfMonth(day)
        items(days.size) { index ->
            // Todo :: 날짜별 다른 디자인 적용
            DhcCalendarDay(
                day = days[index].dayOfMonth,
                modifier = Modifier.aspectRatio(1f),
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
    val colors = LocalDhcColors.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.toString(),
            color = colors.text.textMain,
            style = Body3,
        )
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
            controller = DhcCalendarController()
        )
    }
}
