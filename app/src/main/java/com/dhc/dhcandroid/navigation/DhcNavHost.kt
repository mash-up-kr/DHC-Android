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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.dhc.intro.splash.SplashRoute
import com.dhc.intro.start.IntroRoute

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

        navigation(
            route = DhcRoute.INTRO.route,
            startDestination = DhcRoute.INTRO_START.route,
        ) {
            composable(DhcRoute.INTRO_START.route) {
                IntroRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_DESCRIPTION) },
                )
            }
            composable(DhcRoute.INTRO_DESCRIPTION.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Description")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_FORTUNE_CARD) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_FORTUNE_CARD.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Fortune Card")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_FORTUNE_DETAIL) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_FORTUNE_DETAIL.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Fortune Detail")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_MISSION) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_MISSION.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Mission")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_GENDER) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_GENDER.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Gender")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_BIRTH_DAY) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_BIRTH_DAY.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Birth Day")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_BIRTH_TIME) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_BIRTH_TIME.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Birth Time")
                    Button(
                        onClick = { navController.navigateTo(DhcRoute.INTRO_CATEGORY) },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
            composable(DhcRoute.INTRO_CATEGORY.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("Intro Category")
                    Button(
                        onClick = { navController.navigateToHome() },
                    ) {
                        Text("Go to Next")
                    }
                }
            }
        }

        composable(DhcRoute.MAIN_MISSION.route) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Mission")
                Button(
                    onClick = { navController.navigateToHome() },
                ) {
                    Text("Go to Home")
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
            }
        }
    }
}
