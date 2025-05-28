package com.dhc.dhcandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

class DhcNavHostController(
    val controller: NavHostController,
    startDestination: DhcRoute,
) {
    var currentRoot = startDestination
        private set

    fun navigateTo(route: DhcRoute, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (currentRoot == route) return
        controller.navigate(route.route, builder)
        currentRoot = route
    }

    fun navigateToBottomNavigation(route: DhcRoute) {
        navigateTo(route) {
            popUpTo(controller.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberDhcNavController(
    startDestination: DhcRoute,
): DhcNavHostController {
    val navController = rememberNavController()
    return remember(navController) {
        DhcNavHostController(navController, startDestination)
    }
}
