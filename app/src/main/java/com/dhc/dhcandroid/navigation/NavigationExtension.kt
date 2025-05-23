package com.dhc.dhcandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavController.navigateToHomeFromIntro() {
    navigate(DhcRoutes.MAIN.HOME) {
        popUpTo(DhcRoutes.INTRO) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToBottomNavigation(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToHome() {
    navigateToBottomNavigation(DhcRoutes.MAIN.HOME)
}

fun NavController.navigateToCalendar() {
    navigateToBottomNavigation(DhcRoutes.MAIN.CALENDAR)
}

fun NavController.navigateToMy() {
    navigateToBottomNavigation(DhcRoutes.MAIN.MY)
}
