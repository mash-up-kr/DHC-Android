package com.dhc.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.mypage.MyPageContract.*
import com.dhc.mypage.ui.AppResetDialog
import com.dhc.mypage.ui.MyPageScreen
import kotlinx.coroutines.launch

@Composable
fun MyPageRoute(
    navigateToInitialScreen: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SideEffect.NavigateToIntro -> navigateToInitialScreen()
                is SideEffect.ShowToast -> {
                    coroutineScope.launch {
                        snackBarState.showSnackbar(
                            message = sideEffect.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
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
                onClickDismissButton = {
                    viewModel.sendEvent(Event.ClickDialogDismissButton)
                }
            )
        }

        SnackbarHost(
            hostState = snackBarState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
