package com.dhc.reward.yearfortune

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun YearFortuneRoute(
    modifier: Modifier = Modifier,
    viewModel: YearFortuneViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when (state.yearFortuneState) {
        YearFortuneContract.YearFortuneState.Loading -> {
            YearFortuneLoadingScreen(modifier = modifier)
        }
        YearFortuneContract.YearFortuneState.Success -> {
            YearFortuneScreen(
                yearFortuneInfo = state.yearFortuneInfo,
                isSampleData = state.isSampleData,
                modifier = modifier
            )
        }
        YearFortuneContract.YearFortuneState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "오류가 발생했습니다.")
            }
        }
    }
}
