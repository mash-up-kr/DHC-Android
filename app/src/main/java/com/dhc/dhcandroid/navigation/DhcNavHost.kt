package com.dhc.dhcandroid.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dhc.home.HomeRoute
import androidx.navigation.compose.rememberNavController
import com.dhc.intro.intro.IntroRoute
import com.dhc.intro.splash.SplashRoute


@Composable
fun DhcNavHost(
    startDestination: DhcRoute,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(DhcRoute.SPLASH.route) {
            SplashRoute(
                navigateToNextScreen = {
                    navController.navigateToIntroFromSplash()
                },
            )
        }

        composable(DhcRoute.INTRO.route) {
            IntroRoute(
                navigateToNextScreen = {
                    navController.navigateToHome()
                },
            )
        }

        composable(DhcRoute.MAIN_HOME.route) {
            HomeRoute()
        }

        composable(DhcRoute.MAIN_CALENDAR.route) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Calendar")
                Button(
                    onClick = { navController.navigateToHome() },
                ) {
                    Text("Go to Home")
                }
                Button(
                    onClick = { navController.navigateToMy() },
                ) {
                    Text("Go to My")
                }
            }
        }

        composable(DhcRoute.MAIN_MY.route) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("My")
                Button(
                    onClick = { navController.navigateToHome() },
                ) {
                    Text("Go to Home")
                }
                Button(
                    onClick = { navController.navigateToCalendar() },
                ) {
                    Text("Go to Calendar")
                }
            }
        }
    }
}
