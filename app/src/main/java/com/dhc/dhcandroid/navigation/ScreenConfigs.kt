package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.topbar.model.DhcTopBarState
import com.dhc.designsystem.gnb.model.DhcBottomBarState

data class ScreenConfig(
    val topBarState: DhcTopBarState = DhcTopBarState.None,
    val bottomBarState: DhcBottomBarState = DhcBottomBarState.None,
)
