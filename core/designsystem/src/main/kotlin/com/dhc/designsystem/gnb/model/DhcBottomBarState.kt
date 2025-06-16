package com.dhc.designsystem.gnb.model

import com.dhc.designsystem.R

sealed interface DhcBottomBarState {

    data object None : DhcBottomBarState

    data object BottomNavigation : DhcBottomBarState {
        val items = listOf(
            GnbItem(
                iconResource = R.drawable.ico_home,
                iconText = R.string.btn_bottom_home,
                routeName = "MAIN_HOME",
            ),
            GnbItem(
                iconResource = R.drawable.ico_chart,
                iconText = R.string.btn_bottom_mission,
                routeName = "MAIN_MISSION",
            ),
            GnbItem(
                iconResource = R.drawable.ico_mypage,
                iconText = R.string.btn_bottom_my_page,
                routeName = "MAIN_MY",
            ),
        )
    }
}
