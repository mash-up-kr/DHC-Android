package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhc.dhcandroid.navigation.DhcRoutes.Companion.getRouteByPattern

@Composable
fun DhcApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screenConfig by remember(currentRoute) {
        mutableStateOf(ScreenConfigs.getConfig(getRouteByPattern(currentRoute)))
    }

    Scaffold(
        topBar = {
            if (screenConfig.showTopBar) {
                // Todo :: Top bar Component
                Text(screenConfig.title)
            }
        },
        bottomBar = {
            if (screenConfig.showBottomBar) {
                // Todo :: Bottom navigation bar Component
                Text("바텀 내비게이션 바")
            }
        },
    ) { paddingValues ->
        DhcNavHost(
            navController = navController,
            startDestination = DhcRoutes.INTRO.route,
            modifier = Modifier.padding(paddingValues),
        )
    }
}
