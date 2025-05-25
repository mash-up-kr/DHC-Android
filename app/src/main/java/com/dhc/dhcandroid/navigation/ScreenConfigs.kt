package com.dhc.dhcandroid.navigation

data class ScreenConfig(
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = false,
    val title: String = "",
    val showBackButton: Boolean = false,
)

object ScreenConfigs {
    private val configs = mapOf(
        DhcRoutes.INTRO to ScreenConfig(
            showTopBar = false,
            showBottomBar = false,
            title = "Intro",
        ),
        DhcRoutes.MAIN_HOME to ScreenConfig(
            showTopBar = false,
            showBottomBar = true,
            title = "Home",
        ),
        DhcRoutes.MAIN_CALENDAR to ScreenConfig(
            showTopBar = true,
            showBottomBar = true,
            title = "이번 달 미션 현황",
        ),
        DhcRoutes.MAIN_MY to ScreenConfig(
            showTopBar = false,
            showBottomBar = true,
            title = "My",
        ),
    )

    fun getConfig(route: DhcRoutes): ScreenConfig {
        return configs[route] ?: ScreenConfig()
    }
}
