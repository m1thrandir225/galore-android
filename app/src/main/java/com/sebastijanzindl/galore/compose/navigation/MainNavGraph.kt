package com.sebastijanzindl.galore.compose.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.home.HomeScreen
import com.sebastijanzindl.galore.compose.screens.home.HomeScreenViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Welcome.route, route = "main") {
        composable(route = Screen.Home.route) {
            HomeScreen(viewModel = hiltViewModel<HomeScreenViewModel>(), navigateToAuth = {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(Screen.Welcome.route)
                }
            })
        }
    }
}