package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.R
import com.dhc.designsystem.gnb.model.DhcBottomBarState
import com.dhc.designsystem.gnb.model.GnbItem

object NavigationItemConst {
    val mainGnbItemList = DhcBottomBarState.BottomNavigation(
        listOf(
            GnbItem(
                iconResource = R.drawable.ico_home,
                iconText = R.string.btn_bottom_home,
                routeName = "main/home",
            ),
            GnbItem(
                iconResource = R.drawable.ico_chart,
                iconText = R.string.btn_bottom_mission,
                routeName = "main/mission",
            ),
            GnbItem(
                iconResource = R.drawable.ico_mypage,
                iconText = R.string.btn_bottom_my_page,
                routeName = "main/my",
            ),
        )
    )
}
