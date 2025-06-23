package com.dhc.home.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckDetailScreen

@Composable
fun MonetaryLuckDetailRoute(
    viewModel: MonetaryLuckDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    MonetaryLuckDetailScreen(
        monetaryLuckInfo = state.monetaryLuckInfo
    )
}