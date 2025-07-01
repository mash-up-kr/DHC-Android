package com.dhc.missionstatus

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.designsystem.calendar.rememberDhcCalendarController
import com.dhc.missionstatus.ui.MissionStatusScreen

@Composable
fun MissionStatusRoute(
    viewModel: MissionStatusViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val calendarController = rememberDhcCalendarController()

    LaunchedEffect(Unit) {
        viewModel.loadAnalysisUiData()
    }

    LaunchedEffect(calendarController.currentDate) {
        viewModel.getCalendarData(
            yearMonth = calendarController.currentDate,
        ).forEach { (localDate, dhcCalendarMonthData) ->
            calendarController.updateCalendarMonthData(
                date = localDate,
                monthData = dhcCalendarMonthData,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->

        }
    }

    MissionStatusScreen(
        state = state,
        scrollState = scrollState,
        calendarController = calendarController,
    )
}
