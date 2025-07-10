package com.dhc.dhcandroid.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.gnb.DhcBottomBar
import com.dhc.designsystem.topbar.DhcTopBar
import com.dhc.dhcandroid.MainViewModel

@Composable
fun DhcApp(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val currentScreenConfig by currentScreenConfigAsState(navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: DhcRoute.NONE.route
    val colors = LocalDhcColors.current

    val state by mainViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            DhcTopBar(
                state = currentScreenConfig.topBarState,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
            )
        },
        bottomBar = {
            DhcBottomBar(
                state = currentScreenConfig.bottomBarState,
                currentRoute = DhcRoute.fromRoute(currentRoute).name,
                navigateToRoute = { navController.navigateToBottomNavigation(DhcRoute.fromName(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
            )
        },
        containerColor = colors.background.backgroundMain,
        modifier = modifier,
    ) { paddingValues ->
        AnimatedContent(
            targetState = currentScreenConfig.containerBackground,
            label = "ContainerBackground",
            contentKey = { currentState ->
                when (currentState) {
                    is ContainerBackground.BackgroundWithTopRightGradientColor -> currentState.composeColor
                    is ContainerBackground.ComposeColor -> currentState.composeColor
                    is ContainerBackground.Default -> currentState::class.java.simpleName
                }
            },
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            modifier = Modifier.fillMaxSize(),
        ) { currentState ->
            currentState.Background(modifier = Modifier.fillMaxSize())
        }

        DhcNavHost(
            navController = navController,
            startPage = state.startPage,
            isShowNextPage = state.isShowNextPage,
            triggerShowNextPage = mainViewModel::triggerShowNextPage,
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
