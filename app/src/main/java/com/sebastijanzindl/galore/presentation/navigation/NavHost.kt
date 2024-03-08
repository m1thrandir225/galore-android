package com.sebastijanzindl.galore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun GaloreNavHost(
    isLoggedIn: Boolean,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination =  NavGraph.Splash.route
    ) {
        splashNavGraph(navController)
        onboardingNavGraph(navController)
        authNavGraph(navController)
        mainNavGraph(navController)

    }
}