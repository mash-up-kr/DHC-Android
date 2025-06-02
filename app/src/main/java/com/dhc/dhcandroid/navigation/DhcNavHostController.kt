package com.dhc.dhcandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

class DhcNavHostController(
    val controller: NavHostController,
    startDestination: DhcRoute,
) {
    private val _currentRoot = mutableStateOf(startDestination)
    val currentRoot: State<DhcRoute> = _currentRoot

    fun navigateTo(route: DhcRoute, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (currentRoot.value == route) return
        controller.navigate(route.route, builder)
        _currentRoot.value = route
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
