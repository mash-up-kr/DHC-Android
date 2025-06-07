package com.dhc.designsystem.topbar

import androidx.compose.runtime.Composable
import com.dhc.designsystem.topbar.model.DhcTopBarState

@Composable
fun DhcTopBar(
    state: DhcTopBarState,
    navigateUp: () -> Unit,
) {
    when (state) {
        is DhcTopBarState.Basic -> {
            DhcBasicTopBar(
                title = state.title,
                isShowBackButton = state.isShowBackButton,
                topBarPageState = state.topBarPageState,
                onClickBackButton = navigateUp,
            )
        }

        is DhcTopBarState.None -> Unit
    }
}
