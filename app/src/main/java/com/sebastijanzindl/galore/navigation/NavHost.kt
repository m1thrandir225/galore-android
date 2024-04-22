package com.sebastijanzindl.galore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sebastijanzindl.galore.navigation.graph.NavGraph
import com.sebastijanzindl.galore.navigation.graph.authNavGraph
import com.sebastijanzindl.galore.navigation.graph.mainNavGraph
import com.sebastijanzindl.galore.navigation.graph.onboardingNavGraph
import com.sebastijanzindl.galore.navigation.graph.settingsNavGraph
import com.sebastijanzindl.galore.navigation.graph.splashNavGraph

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
        settingsNavGraph(navController)
    }
}