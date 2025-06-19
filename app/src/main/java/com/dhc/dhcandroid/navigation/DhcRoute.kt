package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.gnb.model.DhcBottomBarState
import com.dhc.designsystem.topbar.model.DhcTopBarState
import com.dhc.designsystem.topbar.model.TopBarPageState

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
    INTRO_START(
        route = "intro/start",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_DESCRIPTION(
        route = "intro/description",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_FORTUNE_CARD(
        route = "intro/fortuneCard",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_FORTUNE_DETAIL(
        route = "intro/fortuneDetail",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_MISSION(
        route = "intro/mission",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_GENDER(
        route = "intro/gender",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_BIRTH_DAY(
        route = "intro/birthDay",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_BIRTH_TIME(
        route = "intro/birthTime",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_CATEGORY(
        route = "intro/category",
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "",
                isShowBackButton = true,
                topBarPageState = TopBarPageState(
                    currentPage = 4,
                    totalPage = 4,
                ),
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
