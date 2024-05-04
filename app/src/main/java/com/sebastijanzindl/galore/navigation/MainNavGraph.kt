package com.sebastijanzindl.galore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.favouriteCocktails.FavouriteCocktailsScreen
import com.sebastijanzindl.galore.presentation.screens.generateCocktail.GenerateCocktailScreen
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen
import com.sebastijanzindl.galore.presentation.screens.search.SearchScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        composable(
            route = AppScreen.Main.Home.route,
        ) {
            HomeScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }

        composable(
            route = AppScreen.Main.Search.route
        ) {
            SearchScreen(
               modifier = Modifier.padding(paddingValues)
            )
        }

        composable(
            route = AppScreen.Main.Generate.route
        ) {
            GenerateCocktailScreen(
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable(
            route = AppScreen.Main.Library.route
        ) {
            FavouriteCocktailsScreen(
                modifier = Modifier.padding(paddingValues)
            )

        }
    }
}