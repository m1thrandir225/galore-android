package com.sebastijanzindl.galore.navigation.graph
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen


fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Welcome.route, route = NavGraph.Main.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToAuth = {
                    navController.navigate(NavGraph.Auth.route) {
                        popUpTo(NavGraph.Main.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToSettings = {
                    navController.navigate(NavGraph.Settings.route);
                },
                navigateToHelp = {},
                navController = navController
            )
        }

    }
}