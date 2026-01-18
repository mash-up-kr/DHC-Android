package com.dhc.dhcandroid.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import com.dhc.reward.RewardRoute
import com.dhc.reward.yearfortune.YearFortuneRoute
import com.example.survey.SurveyRoute

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
        startDestination = startDestination.route,
    ) {
        navigation(
            route = DhcRoute.INTRO.route,
            startDestination = DhcRoute.INTRO_START.route,
        ) {
            composable(
                route = DhcRoute.INTRO_START.route,
                exitTransition = { ExitTransition.None },
            ) {
                IntroRoute(
                    navigateToNextScreen = { navController.navigateTo(DhcRoute.INTRO_DESCRIPTION) },
                )
            }
            composable(
                route = DhcRoute.INTRO_DESCRIPTION.route,
                enterTransition = { EnterTransition.None },
            ) {
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

        composable(DhcRoute.MAIN_REWARD.route) {
            RewardRoute(
                navigateToYearFortune = { navController.navigateTo(DhcRoute.YEAR_FORTUNE) },
            )
        }

        composable(DhcRoute.MAIN_MY.route) {
            MyPageRoute(
                navigateToInitialScreen = { navController.navigateToIntroPageWithClearStack() },
                navigateToFortuneSurvey = { navController.navigateTo(DhcRoute.FORTUNE_SURVEY) },
            )
        }

        composable(DhcRoute.MAIN_HOME.route) { navBackStackEntry ->
            HomeRoute(
                navBackStackEntry = navBackStackEntry,
                navigateToMission = { navController.navigateToMission() },
                navigateToMonetaryLuckDetail = { navController.navigateTo(DhcRoute.HOME_MONETARY_DETAIL) },
                navigateToFortuneSurvey = { navController.navigateTo(DhcRoute.FORTUNE_SURVEY) },
                navigateToReward = { navController.navigateToReward() },
            )
        }

        composable(DhcRoute.HOME_MONETARY_DETAIL.route) {
            MonetaryLuckDetailRoute()
        }

        composable(DhcRoute.FORTUNE_SURVEY.route) {
            SurveyRoute(
                navigateToHome = { navController.navigateToHome() },
                navigateToPrevScreen = { navController.navigateUp() },
            )
        }
        composable(DhcRoute.YEAR_FORTUNE.route) {
            YearFortuneRoute()
        }
    }
}
