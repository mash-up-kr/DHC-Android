package com.dhc.home.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckDetail

@Composable
fun MonetaryLuckDetailRoute(
    viewModel: MonetaryLuckDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    MonetaryLuckDetail(
        monetaryLuckInfo = state.monetaryLuckInfo
    )
}