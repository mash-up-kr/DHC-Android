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
import com.dhc.home.HomeRoute
import com.dhc.home.detail.MonetaryLuckDetailRoute
import com.dhc.intro.birthday.IntroBirthDayRoute
import com.dhc.intro.birthtime.IntroBirthTimeRoute
import com.dhc.intro.category.IntroCategoryRoute
import com.dhc.intro.description.IntroDescriptionRoute
import com.dhc.intro.fortunecard.IntroFortuneCardRoute
import com.dhc.intro.gender.IntroGenderRoute
import com.dhc.intro.mission.IntroMissionRoute
import com.dhc.intro.splash.SplashRoute
import com.dhc.intro.start.IntroRoute
import com.dhc.missionstatus.MissionStatusRoute
import com.dhc.mypage.MyPageRoute

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
                    // navController.navigateToIntroFromSplash()
                    navController.navigateToHomeFromIntro()
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
                IntroDescriptionRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_FORTUNE_CARD) },
                )
            }
            composable(DhcRoute.INTRO_FORTUNE_CARD.route) {
                IntroFortuneCardRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_FORTUNE_DETAIL) },
                )
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
                IntroMissionRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_GENDER) },
                )
            }
            composable(DhcRoute.INTRO_GENDER.route) {
                IntroGenderRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_BIRTH_DAY) },
                )
            }
            composable(DhcRoute.INTRO_BIRTH_DAY.route) {
                IntroBirthDayRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_BIRTH_TIME) },
                )
            }
            composable(DhcRoute.INTRO_BIRTH_TIME.route) {
                IntroBirthTimeRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_CATEGORY) },
                )
            }
            composable(DhcRoute.INTRO_CATEGORY.route) {
                IntroCategoryRoute(
                    navigateToNextScreen = { navController.navigateToHomeFromIntro() },
                )
            }
        }

        composable(DhcRoute.MAIN_MISSION.route) {
            MissionStatusRoute()
        }

        composable(DhcRoute.MAIN_MY.route) {
            MyPageRoute(
                navigateToInitialScreen = { navController.navigateToIntroPageWithClearStack() },
            )
        }
        composable(DhcRoute.MAIN_HOME.route) {
            HomeRoute(
                navigateToMission = { navController.navigateToMission() },
                navigateToMonetaryLuckDetail = { navController.navigateTo(DhcRoute.HOME_MONETARY_DETAIL) },
            )
        }

        composable(DhcRoute.HOME_MONETARY_DETAIL.route) {
            MonetaryLuckDetailRoute()
        }
    }
}
