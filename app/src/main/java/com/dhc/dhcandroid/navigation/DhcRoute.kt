package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.topbar.model.DhcTopBarState

enum class DhcRoute(
    val route: String,
    val screenConfig: ScreenConfig,
) {
    SPLASH(
        route = "splash",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO(
        route = "intro",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    MAIN_HOME(
        route = "main/home",
        screenConfig = ScreenConfig(
            bottomBarState = DhcBottomBarState.BottomNavigation,
        ),
    ),
    MAIN_MISSION(
        route = "main/mission",
        screenConfig = ScreenConfig(
            bottomBarState = DhcBottomBarState.BottomNavigation,
        ),
    ),
    MAIN_MY(
        route = "main/my",
        screenConfig = ScreenConfig(
            bottomBarState = DhcBottomBarState.BottomNavigation,
        ),
    ),
    NONE(
        route = "none",
        screenConfig = ScreenConfig(),
    ), ;

    companion object {
        fun fromName(name: String): DhcRoute {
            return entries.find { it.name == name } ?: NONE
        }

        fun fromRoute(route: String): DhcRoute {
            return entries.find { it.route.replace("{id}", "[^/]+").toRegex().matches(route) }
                ?: NONE
        }
    }
}
