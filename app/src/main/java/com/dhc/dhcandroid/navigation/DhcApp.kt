package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.gnb.DhcBottomBar
import com.dhc.designsystem.topbar.DhcTopBar
import com.dhc.dhcandroid.MainViewModel

// MainViewModel.init() 실행을 위해 미사용이지만 파라미터가 필요합니다
@Suppress("UnusedParameter")
@Composable
fun DhcApp(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val colors = LocalDhcColors.current
    val startDestination = DhcRoute.SPLASH
    val navController = rememberNavController()
    val currentScreenConfig by currentScreenConfigAsState(navController)

    Scaffold(
        topBar = {
            DhcTopBar(
                state = currentScreenConfig.topBarState,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
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
        containerColor = when (val containerColor = currentScreenConfig.containerColor) {
            is ContainerColor.Default -> colors.background.backgroundMain
            is ContainerColor.ComposeColor -> containerColor.color
        },
        modifier = modifier,
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
