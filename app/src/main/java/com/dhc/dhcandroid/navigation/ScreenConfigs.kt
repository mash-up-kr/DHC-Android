package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.gnb.model.DhcBottomBarState

data class ScreenConfig(
    val topBarState: DhcTopBarState = DhcTopBarState.None,
    val bottomBarState: DhcBottomBarState = DhcBottomBarState.None,
)

sealed interface DhcTopBarState {
    data object None : DhcTopBarState
    data class CenterTitle(
        val title: String,
        val showBackButton: Boolean,
    ) : DhcTopBarState
}
