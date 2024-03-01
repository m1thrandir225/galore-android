package com.sebastijanzindl.galore.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Splash.route, route = NavGraph.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true;
                        }
                    }
                },
                navigateToMain = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
    }
}