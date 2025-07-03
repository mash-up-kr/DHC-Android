package com.dhc.home.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.home.detail.MoneyDetailLuckContract.Event
import com.dhc.home.detail.MoneyDetailLuckContract.SideEffect
import com.dhc.home.main.HomeContract
import com.dhc.presentation.mvi.EventHandler
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckDetailScreen

@Composable
fun MonetaryLuckDetailRoute(
    navigateToMissionConfirm: () -> Unit = {},
    isShowButton: Boolean = false,
    viewModel: MonetaryLuckDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInitialData()
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SideEffect.NavigateToMissionConfirm -> { navigateToMissionConfirm() }
            }

        }
    }

    MonetaryLuckDetailScreen(
        monetaryLuckInfo = state.monetaryLuckInfo,
        isShowButton = isShowButton,
        onClickButton = { viewModel.sendEvent(Event.ClickConfirm) },
    )
}
