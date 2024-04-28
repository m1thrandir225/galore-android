package com.sebastijanzindl.galore.navigation.graph
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.screens.favouriteCocktails.FavouriteCocktailsScreen
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen
import com.sebastijanzindl.galore.presentation.screens.search.SearchScreen


fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Welcome.route, route = NavGraph.Main.route
    ) {

        val  currentRoute = navController.currentDestination

        val navigateToHome = {
            navController.navigate(Screen.Home.route);
        }
        val navigateToSearch = {
            navController.navigate(Screen.Search.route);
        }

        val navigateToGenerate = {
            navController.navigate(Screen.GenerateCocktail.route);
        }

        val navigateToLibrary = {
            navController.navigate(Screen.Favourites.route);
        }


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
                navigateToGenerateCocktails = navigateToGenerate,
                navigateToLibrary = navigateToLibrary,
                navigateToSearch = navigateToSearch,
                currentRoute = currentRoute,
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(
                navigateToHome = navigateToHome,
                navigateToFavouriteCocktails = navigateToLibrary,
                navigateToGenerateCocktails = navigateToGenerate,
                currentRoute = currentRoute
            )
        }
        composable(route = Screen.Favourites.route) {
            FavouriteCocktailsScreen(
                navigateToHome = navigateToHome,
                navigateToSearch = navigateToSearch,
                navigateToGenerateCocktails = navigateToGenerate,
                currentRoute = currentRoute
            )
        }

    }
}