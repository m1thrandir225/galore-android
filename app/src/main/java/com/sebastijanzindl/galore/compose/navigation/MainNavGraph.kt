package com.sebastijanzindl.galore.compose.navigation
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Welcome.route, route = "main") {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(NavGraph.Main.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}