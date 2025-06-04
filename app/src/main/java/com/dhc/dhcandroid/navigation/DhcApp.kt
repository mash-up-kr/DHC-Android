package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhc.designsystem.gnb.DhcGnb

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
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            DhcNavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        }
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

// Todo :: 추후 디자인 시스템 모듈로 이동하여 사용하자
@Composable
fun DhcBottomBar(
    state: DhcBottomBarState,
    modifier: Modifier = Modifier,
    navigateToRoute: (DhcRoute) -> Unit = {},
) {
    when (state) {
        is DhcBottomBarState.BottomNavigation -> {
            // Todo :: Bottom navigation bar Component
            DhcGnb(
                gnbItemList = state.items,
                selectedIndex = 0,
                onClickItem = { routeName -> navigateToRoute(DhcRoute.fromName(routeName)) },
                modifier = modifier,
            )
        }

        is DhcBottomBarState.None -> {
            // No bottom bar
        }
    }
}
