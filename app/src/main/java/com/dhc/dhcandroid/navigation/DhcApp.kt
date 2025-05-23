package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun DhcApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute by remember {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    val screenConfig by remember {
        derivedStateOf { ScreenConfigs.getConfigByPattern(currentRoute) }
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
        }
    ) { paddingValues ->
        DhcNavHost(
            navController = navController,
            startDestination = DhcRoutes.INTRO,
            modifier = Modifier.padding(paddingValues),
        )
    }
}
