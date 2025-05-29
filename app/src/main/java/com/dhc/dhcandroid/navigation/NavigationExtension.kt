package com.dhc.dhcandroid.navigation

fun DhcNavHostController.navigateToIntroFromSplash() {
    navigateTo(DhcRoute.INTRO) {
        popUpTo(DhcRoute.SPLASH.route) {
            inclusive = true
        }
    }
}

fun DhcNavHostController.navigateToHomeFromIntro() {
    navigateTo(DhcRoute.MAIN_HOME) {
        popUpTo(DhcRoute.INTRO.route) {
            inclusive = true
        }
    }
}

fun DhcNavHostController.navigateToHome() {
    navigateToBottomNavigation(DhcRoute.MAIN_HOME)
}

fun DhcNavHostController.navigateToCalendar() {
    navigateToBottomNavigation(DhcRoute.MAIN_CALENDAR)
}

fun DhcNavHostController.navigateToMy() {
    navigateToBottomNavigation(DhcRoute.MAIN_MY)
}
