package com.sebastijanzindl.galore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        composable(
            route = AppScreen.Main.Home.route,
        ) {
            HomeScreen(
                navigateToAuth = { /*TODO*/ },
                navigateToSettings = { /*TODO*/ },
                navigateToHelp = { /*TODO*/ },
                navigateToSearch = { /*TODO*/ },
                navigateToGenerateCocktails = { /*TODO*/ },
                navigateToLibrary = { /*TODO*/ },
            )
        }
    }
}