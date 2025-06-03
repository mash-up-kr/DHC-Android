package com.dhc.dhcandroid.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

private fun NavHostController.navigateTo(route: DhcRoute, builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route.route, builder)
}

fun NavHostController.navigateToHomeFromIntro() {
    navigateTo(DhcRoute.MAIN_HOME) {
        popUpTo(DhcRoute.INTRO.route) {
            inclusive = true
        }
    }
}

private fun NavHostController.navigateToBottomNavigation(route: DhcRoute) {
    navigateTo(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToHome() {
    navigateToBottomNavigation(DhcRoute.MAIN_HOME)
}

fun NavHostController.navigateToCalendar() {
    navigateToBottomNavigation(DhcRoute.MAIN_CALENDAR)
}

fun NavHostController.navigateToMy() {
    navigateToBottomNavigation(DhcRoute.MAIN_MY)
}
