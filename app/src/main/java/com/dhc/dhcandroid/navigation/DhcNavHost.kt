package com.dhc.dhcandroid.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
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
import com.dhc.intro.fortunedetail.IntroFortuneDetailRoute
import com.dhc.intro.gender.IntroGenderRoute
import com.dhc.intro.mission.IntroMissionRoute
import com.dhc.intro.splash.SplashRoute
import com.dhc.intro.start.IntroRoute
import com.dhc.missionstatus.MissionStatusRoute
import com.dhc.mypage.MyPageRoute

@Composable
fun DhcNavHost(
    isShowNextPage: Boolean,
    startPage: DhcRoute,
    triggerShowNextPage: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Crossfade(
        targetState = isShowNextPage && startPage != DhcRoute.NONE,
    ) { isSplash ->
        if (isSplash) {
            DhcNavHost(
                navController = navController,
                startDestination = startPage,
                modifier = modifier.fillMaxWidth(),
            )
        } else {
            SplashRoute(
                navigateToNextScreen = {
                    triggerShowNextPage()
                },
            )
        }
    }
}

@Composable
fun DhcNavHost(
    startDestination: DhcRoute,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DhcRoute.INTRO.route,
//        startDestination = startDestination.route,
    ) {
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
                IntroFortuneDetailRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_MISSION) },
                )
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
