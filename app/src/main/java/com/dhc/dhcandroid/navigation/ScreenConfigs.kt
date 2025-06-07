package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.topbar.model.DhcTopBarState

data class ScreenConfig(
    val topBarState: DhcTopBarState = DhcTopBarState.None,
    val bottomBarState: DhcBottomBarState = DhcBottomBarState.None,
)

sealed interface DhcBottomBarState {
    val items: List<BottomNavigationItem>

    data object None : DhcBottomBarState {
        override val items: List<BottomNavigationItem> = emptyList()
    }

    data object BottomNavigation : DhcBottomBarState {
        override val items = listOf(
            BottomNavigationItem(
                name = "Home",
                icon = "ic_home",
                route = DhcRoute.MAIN_HOME.name,
            ),
            BottomNavigationItem(
                name = "Calendar",
                icon = "ic_calendar",
                route = DhcRoute.MAIN_CALENDAR.name,
            ),
            BottomNavigationItem(
                name = "My",
                icon = "ic_my",
                route = DhcRoute.MAIN_MY.name,
            ),
        )
    }
}

data class BottomNavigationItem(
    val name: String,
    val icon: String,
    val route: String,
)
