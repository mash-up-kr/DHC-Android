package com.dhc.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.mypage.MyPageContract.*
import com.dhc.mypage.ui.AppResetDialog
import com.dhc.mypage.ui.MyPageScreen

@Composable
fun MyPageRoute(
    navigateToInitialScreen: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SideEffect.NavigateToIntro -> navigateToInitialScreen()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadMyPageData()
    }

    Box {
        MyPageScreen(
            state = state,
            eventHandler = viewModel::sendEvent,
        )

        if (state.isShowAppResetDialog) {
            AppResetDialog(
                onClickAppResetButton = {
                    viewModel.sendEvent(Event.ClickAppResetConfirmButton)
                },
                onDismissButton = {
                    viewModel.sendEvent(Event.ClickDialogDismissButton)
                }
            )
        }
    }
}
