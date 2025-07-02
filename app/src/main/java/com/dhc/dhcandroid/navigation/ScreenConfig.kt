package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.gnb.model.DhcBottomBarState
import com.dhc.designsystem.topbar.model.DhcTopBarState

data class ScreenConfig(
    val topBarState: DhcTopBarState = DhcTopBarState.None,
    val bottomBarState: DhcBottomBarState = DhcBottomBarState.None,
    val bottomBarSelectedIndex: Int = 0,
)
