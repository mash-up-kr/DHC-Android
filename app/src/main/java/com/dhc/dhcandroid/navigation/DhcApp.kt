package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhc.designsystem.gnb.DhcBottomBar

@Composable
fun DhcApp() {
    val startDestination = DhcRoute.SPLASH
    val navController = rememberNavController()
    val currentScreenConfig by currentScreenConfigAsState(navController)

    Scaffold(
        topBar = {
            DhcTopBar(currentScreenConfig.topBarState)
        },
        bottomBar = {
            DhcBottomBar(
                state = currentScreenConfig.bottomBarState,
                navigateToRoute = { navController.navigateToBottomNavigation(DhcRoute.fromName(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(60.dp),
            )
        },
    ) { paddingValues ->
        DhcNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun currentScreenConfigAsState(navController: NavHostController): State<ScreenConfig> {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route ?: DhcRoute.NONE.route

    return remember(route) {
        derivedStateOf {
            DhcRoute.fromRoute(route).screenConfig
        }
    }
}

// Todo :: 추후 디자인 시스템 모듈로 이동하여 사용하자
@Composable
fun DhcTopBar(state: DhcTopBarState) {
    when (state) {
        is DhcTopBarState.CenterTitle -> {
            // Todo :: Top bar Component
            Text(state.title)
        }

        is DhcTopBarState.None -> {
            // No top bar
        }
    }
}
