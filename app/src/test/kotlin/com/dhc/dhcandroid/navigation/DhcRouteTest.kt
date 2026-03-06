package com.dhc.dhcandroid.navigation

import org.junit.Test

class DhcRouteTest {
    @Test
    fun `fromRoute() Test`() {
        val routeListTestCase: List<Pair<String, DhcRoute>> = listOf(
            Pair("intro", DhcRoute.INTRO),
            Pair("main/home", DhcRoute.MAIN_HOME),
            Pair("main/mission", DhcRoute.MAIN_MISSION),
            Pair("main/my", DhcRoute.MAIN_MY),
            Pair("main/", DhcRoute.NONE),
            Pair("dhcdhcdhc/dhc", DhcRoute.NONE),
        )

        routeListTestCase.forEach { (route, dhcRoute) ->
            assert(DhcRoute.fromRoute(route) == dhcRoute)
        }
    }
}
