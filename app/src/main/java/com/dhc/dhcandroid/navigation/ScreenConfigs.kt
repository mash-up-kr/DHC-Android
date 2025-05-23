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
        DhcRoutes.MAIN.HOME to ScreenConfig(
            showTopBar = false,
            showBottomBar = true,
            title = "Home",
        ),
        DhcRoutes.MAIN.CALENDAR to ScreenConfig(
            showTopBar = true,
            showBottomBar = true,
            title = "이번 달 미션 현황",
        ),
        DhcRoutes.MAIN.MY to ScreenConfig(
            showTopBar = false,
            showBottomBar = true,
            title = "My",
        ),
    )

    fun getConfigByPattern(route: String?): ScreenConfig {
        if (route == null) return ScreenConfig()

        configs[route]?.let { return it }

        return configs.entries.find { ( pattern, _ ) ->
            matchesPattern(route, pattern)
        }?.value ?: ScreenConfig()
    }

    private fun matchesPattern(actualRoute: String, pattern: String): Boolean {

        // 예시들:
        // pattern: "detail/{id}" -> actualRoute: "detail/123"
        // pattern: "user/{userId}/post/{postId}" -> actualRoute: "user/456/post/789"
        // pattern: "category/{category}" -> actualRoute: "category/electronics"
        val regex = pattern
            .replace("{", "\\{")
            .replace("}", "\\}")
            .replace("\\{[^}]*\\}", "[^/]+")  // {id} -> [^/]+ (슬래시가 아닌 모든 문자)
            .toRegex()

        return actualRoute.matches("^$regex$".toRegex())
    }
}