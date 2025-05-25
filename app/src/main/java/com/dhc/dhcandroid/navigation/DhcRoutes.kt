package com.dhc.dhcandroid.navigation

enum class DhcRoutes(val route: String) {
    INTRO("intro"),
    MAIN_HOME("main/home"),
    MAIN_CALENDAR("main/calendar"),
    MAIN_MY("main/my"),
    NONE("none");

    companion object {
        fun getRouteByPattern(pattern: String?): DhcRoutes {

            // 예시들:
            // pattern: "detail/{id}" -> actualRoute: "detail/123"
            // pattern: "user/{userId}/post/{postId}" -> actualRoute: "user/456/post/789"
            // pattern: "category/{category}" -> actualRoute: "category/electronics"
            val regex = pattern
                ?.replace("{", "\\{")
                ?.replace("}", "\\}")
                ?.replace("\\{[^}]*\\}", "[^/]+")  // {id} -> [^/]+ (슬래시가 아닌 모든 문자)
                ?.toRegex() ?: return NONE

            return DhcRoutes.entries.firstOrNull { it.route.matches("^$regex$".toRegex()) } ?: NONE
        }
    }
}
