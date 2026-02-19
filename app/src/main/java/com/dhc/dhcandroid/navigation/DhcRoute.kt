package com.dhc.dhcandroid.navigation

import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.gnb.model.DhcBottomBarState
import com.dhc.designsystem.topbar.model.DhcTopBarState
import com.dhc.designsystem.topbar.model.TopBarPageState

enum class DhcRoute(
    val route: String,
    val screenConfig: ScreenConfig,
) {
    SPLASH(
        route = DhcRouteConst.SPLASH,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO(
        route = DhcRouteConst.INTRO,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_START(
        route = DhcRouteConst.INTRO_START,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
            containerBackground = ContainerBackground.ComposeColor(SurfaceColor.neutral900),
        ),
    ),
    INTRO_DESCRIPTION(
        route = DhcRouteConst.INTRO_DESCRIPTION,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_FORTUNE_CARD(
        route = DhcRouteConst.INTRO_FORTUNE_CARD,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_FORTUNE_DETAIL(
        route = DhcRouteConst.INTRO_FORTUNE_DETAIL,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
            containerBackground = ContainerBackground.BackgroundWithTopRightGradientColor(),
        ),
    ),
    INTRO_MISSION(
        route = DhcRouteConst.INTRO_MISSION,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.None,
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_GENDER(
        route = DhcRouteConst.INTRO_GENDER,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "",
                isShowBackButton = false,
                topBarPageState = TopBarPageState(
                    currentPage = 1,
                    totalPage = 4,
                ),
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_BIRTH_DAY(
        route = DhcRouteConst.INTRO_BIRTH_DAY,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "",
                isShowBackButton = true,
                topBarPageState = TopBarPageState(
                    currentPage = 2,
                    totalPage = 4,
                ),
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_BIRTH_TIME(
        route = DhcRouteConst.INTRO_BIRTH_TIME,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "",
                isShowBackButton = true,
                topBarPageState = TopBarPageState(
                    currentPage = 3,
                    totalPage = 4,
                ),
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    INTRO_CATEGORY(
        route = DhcRouteConst.INTRO_CATEGORY,
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
        route = DhcRouteConst.MAIN_HOME,
        screenConfig = ScreenConfig(
            bottomBarState = NavigationItemConst.mainGnbItemList,
            containerBackground = ContainerBackground.BackgroundWithTopRightGradientColor(),
        ),
    ),
    MAIN_MISSION(
        route = DhcRouteConst.MAIN_MISSION,
        screenConfig = ScreenConfig(
            bottomBarState = NavigationItemConst.mainGnbItemList,
            containerBackground = ContainerBackground.BackgroundWithTopRightGradientColor(),
        ),
    ),
    MAIN_REWARD(
        route = DhcRouteConst.MAIN_REWARD,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "리워드",
                isShowBackButton = false,
            ),
            bottomBarState = NavigationItemConst.mainGnbItemList,
            containerBackground = ContainerBackground.BackgroundWithTopRightGradientColor(),
        ),
    ),
    MAIN_MY(
        route = DhcRouteConst.MAIN_MY,
        screenConfig = ScreenConfig(
            bottomBarState = NavigationItemConst.mainGnbItemList,
        ),
    ),
    HOME_MONETARY_DETAIL(
        route = DhcRouteConst.HOME_MONETARY_DETAIL,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "오늘의 금전운",
                isShowBackButton = true,
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    FORTUNE_SURVEY(
        route = DhcRouteConst.FORTUNE_SURVEY,
        screenConfig = ScreenConfig(),
    ),
    YEAR_FORTUNE(
        route = DhcRouteConst.YEAR_FORTUNE,
        screenConfig = ScreenConfig(
            topBarState = DhcTopBarState.Basic(
                title = "1년 운세",
                isShowBackButton = true,
            ),
            bottomBarState = DhcBottomBarState.None,
        ),
    ),
    NONE(
        route = DhcRouteConst.NONE,
        screenConfig = ScreenConfig(),
    ), ;

    companion object {
        fun fromRoute(route: String): DhcRoute {
            return entries.find {
                it.route
                    .replace("{id}", "[^/]+")
                    .replace("{isSampleData}", "[^&]+")
                    .toRegex()
                    .matches(route)
            } ?: NONE
        }
    }
}
