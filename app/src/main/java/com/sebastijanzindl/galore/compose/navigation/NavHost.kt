package com.sebastijanzindl.galore.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.splash.SplashScreen

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