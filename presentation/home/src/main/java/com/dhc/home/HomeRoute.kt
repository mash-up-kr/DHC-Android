package com.dhc.home

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.home.ui.HomeScreen

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state by viewModel.state.collectAsState()

    HomeScreen(
        scrollState = scrollState,
    )
}