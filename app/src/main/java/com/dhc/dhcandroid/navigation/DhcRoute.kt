package com.dhc.dhcandroid.navigation

enum class DhcRoute(
    val route: String,
    val screenConfig: ScreenConfig,
) {
    INTRO(
        route = "intro",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.CenterTitle(
                title = "Intro",
                showBackButton = false,
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    MAIN_HOME(
        route = "main/home",
        screenConfig = ScreenConfig(
            bottomBarState = DhcBottomBarState.BottomNavigation,
        ),
    ),
    MAIN_CALENDAR(
        route = "main/calendar",
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
        fun from(name: String): DhcRoute {
            return entries.find { it.name == name } ?: NONE
        }
    }
}
