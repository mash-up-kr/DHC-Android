package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.SurfaceColor.neutral700
import com.dhc.designsystem.calendar.DhcCalendarController
import com.dhc.designsystem.calendar.rememberDhcCalendarController
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun DhcCalendar(
    modifier: Modifier = Modifier,
    initialDate: LocalDate = LocalDate.now(),
    controller: DhcCalendarController = rememberDhcCalendarController(initialDate),
) {
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE },
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                controller.onChangePage(page)
            }
    }

    Column(
        modifier = modifier
            .background(color = neutral700)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DhcCalendarHeader(
            modifier = Modifier.fillMaxWidth(),
            currentDate = controller.currentDate,
            onClickLeftButton = {
                scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage - 1)
                }
            },
            onClickRightButton = {
                scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            },
        )
        DhcCalendarDayOfWeek(
            modifier = Modifier.fillMaxWidth(),
        )
        DhcCalendarDateSwiper(
            modifier = Modifier.fillMaxWidth(),
            pagerState = pagerState,
            controller = controller,
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
