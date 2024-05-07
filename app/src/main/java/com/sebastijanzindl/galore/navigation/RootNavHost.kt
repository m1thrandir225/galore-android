package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebastijanzindl.galore.presentation.screens.splash.SplashScreen

@Composable
fun RootNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = AppScreen.SplashScreen.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        authNavGraph(
            navController = navHostController,
            paddingValues = paddingValues
        )
        onboardingNavGraph(
            navController = navHostController
        )
        mainNavGraph(
            navController = navHostController,
            paddingValues = paddingValues
        )
        settingsNavGraph(
            navController =  navHostController,
            paddingValues = paddingValues
        )
        composable(route = AppScreen.SplashScreen.route) {
            SplashScreen(
                navigateToMain = {
                    navHostController.navigate(AppScreen.Main.Home.route) {
                        popUpTo(AppScreen.SplashScreen.route) {
                            inclusive = true;
                        }
                    }
                },
                navigateToAuth = {
                    navHostController.navigate(AppScreen.Auth.Welcome.route) {
                        popUpTo(AppScreen.SplashScreen.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
    }
}