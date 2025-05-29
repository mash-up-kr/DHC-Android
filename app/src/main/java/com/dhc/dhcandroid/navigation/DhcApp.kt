package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DhcApp() {
    val startDestination = DhcRoute.INTRO
    val navController = rememberDhcNavController(startDestination)

    Scaffold(
        topBar = {
            DhcTopBar(navController.currentRoot.screenConfig.topBarState)
        },
        bottomBar = {
            DhcBottomBar(navController.currentRoot.screenConfig.bottomBarState)
        },
    ) { paddingValues ->
        DhcNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues),
        )
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
            Row(
                modifier = modifier,
            ) {
                state.items.forEach { item ->
                    Text(
                        modifier = modifier.clickable {
                            navigateToRoute(DhcRoute.from(item.route))
                        },
                        text = item.name,
                    )
                }
            }
        }
        is DhcBottomBarState.None -> {
            // No bottom bar
        }
    }
}
