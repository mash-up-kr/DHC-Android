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

@Composable
fun DhcNavHost(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(DhcRoutes.INTRO) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Intro")
                Button(
                    onClick = { navController.navigateToHomeFromIntro() }
                ) {
                    Text("Go to Home")
                }
            }
        }

        composable(DhcRoutes.MAIN.HOME) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Home")
                Button(
                    onClick = { navController.navigateToCalendar() }
                ) {
                    Text("Go to Calendar")
                }
                Button(
                    onClick = { navController.navigateToMy() }
                ) {
                    Text("Go to My")
                }
            }
        }

        composable(DhcRoutes.MAIN.CALENDAR) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Calendar")
                Button(
                    onClick = { navController.navigateToHome() }
                ) {
                    Text("Go to Home")
                }
                Button(
                    onClick = { navController.navigateToMy() }
                ) {
                    Text("Go to My")
                }
            }
        }

        composable(DhcRoutes.MAIN.MY) {
            // 아래 내용은 예시
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("My")
                Button(
                    onClick = { navController.navigateToHome() }
                ) {
                    Text("Go to Home")
                }
                Button(
                    onClick = { navController.navigateToCalendar() }
                ) {
                    Text("Go to Calendar")
                }
            }
        }
    }
}