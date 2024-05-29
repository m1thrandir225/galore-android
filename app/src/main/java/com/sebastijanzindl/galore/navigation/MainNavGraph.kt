package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.cocktailDetails.CocktailDetailsScreen
import com.sebastijanzindl.galore.presentation.screens.cocktailSection.CocktailSectionScreen
import com.sebastijanzindl.galore.presentation.screens.generateCocktail.GenerateCocktailScreen
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen
import com.sebastijanzindl.galore.presentation.screens.library.LibraryScreen
import com.sebastijanzindl.galore.presentation.screens.search.SearchScreen
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        fun navigateToCocktailSection(sectionTitle: String) {
            navController.navigate(AppScreen.Main.CocktailSection.route.replace(
                oldValue = "{section-title}",
                newValue = sectionTitle
                )
            );

        }
        fun navigateToCocktailDetailed(cocktailId: String) {
            navController.navigate(AppScreen.Main.CocktailDetails.route.replace(
                oldValue = "{cocktail-id}",
                newValue = cocktailId
            ))
        }
        composable(
            route = AppScreen.Main.Home.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            HomeScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                sharedSectionViewModel = sharedSectionViewModel
            )
        }

        composable(
            route = AppScreen.Main.Search.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            SearchScreen(
               modifier = Modifier
                   .padding(paddingValues)
                   .fillMaxSize()
            )
        }

        composable(
            route = AppScreen.Main.Generate.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            GenerateCocktailScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        }
        composable(
            route = AppScreen.Main.Library.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            LibraryScreen(
                modifier = Modifier.padding(paddingValues),
                sharedSectionViewModel = sharedSectionViewModel,
                navigateToCocktailSection = { title -> navigateToCocktailSection(title) }
            )

        }

        composable(
            route = AppScreen.Main.CocktailSection.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            CocktailSectionScreen(
                modifier = Modifier.padding(paddingValues),
                sharedSectionViewModel = sharedSectionViewModel,
                singleCocktailCardPress = { id ->  navigateToCocktailDetailed(id)}
            )
        }

        composable(
            route = AppScreen.Main.CocktailDetails.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val cocktailId = it.arguments?.getString("cocktail-id");
            CocktailDetailsScreen(
                modifier = Modifier.padding(paddingValues),
                cocktailId = cocktailId
            )
        }

    }
}