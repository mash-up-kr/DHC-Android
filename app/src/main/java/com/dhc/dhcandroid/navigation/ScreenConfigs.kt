package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.R
import com.dhc.designsystem.gnb.model.GnbItem

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

sealed interface DhcBottomBarState {

    data object None : DhcBottomBarState

    data object BottomNavigation : DhcBottomBarState {
        val items = listOf(
            GnbItem(
                iconResource = R.drawable.home_04,
                iconText = R.string.btn_bottom_home,
                routeName = DhcRoute.MAIN_HOME.name,
            ),
            GnbItem(
                iconResource = R.drawable.bar_chart_10,
                iconText = R.string.btn_bottom_mission,
                routeName = DhcRoute.MAIN_MISSION.name,
            ),
            GnbItem(
                iconResource = R.drawable.user_02,
                iconText = R.string.btn_bottom_my_page,
                routeName = DhcRoute.MAIN_MY.name,
            ),
        )
    }
}
