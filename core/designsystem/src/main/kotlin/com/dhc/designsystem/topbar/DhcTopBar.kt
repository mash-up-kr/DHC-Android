package com.dhc.designsystem.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhc.designsystem.topbar.model.DhcTopBarState

@Composable
fun DhcTopBar(
    state: DhcTopBarState,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is DhcTopBarState.Basic -> {
            DhcBasicTopBar(
                title = state.title,
                isShowBackButton = state.isShowBackButton,
                modifier = modifier,
                topBarPageState = state.topBarPageState,
                onClickBackButton = navigateUp,
            )
        }

        is DhcTopBarState.None -> Unit
    }
}
