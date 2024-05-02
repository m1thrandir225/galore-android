package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavHost(
    navHostController: NavHostController,
    isAuthenticated: Boolean
) {
    NavHost(
        navController = navHostController,
        startDestination = if(isAuthenticated) AppScreen.Main.route else AppScreen.Auth.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        authNavGraph(navController = navHostController)
        mainNavGraph(navController = navHostController)
    }
}