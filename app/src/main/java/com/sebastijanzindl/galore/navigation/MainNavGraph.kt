package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import com.sebastijanzindl.galore.presentation.screens.generateCocktailLoading.GenerateLoadingScreen
import com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectCocktails.GenerateSelectCocktailsScreen
import com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectFlavours.GenerateSelectFlavoursScreen
import com.sebastijanzindl.galore.presentation.screens.generatedCocktailDetails.GeneratedCocktailDetailsScreen
import com.sebastijanzindl.galore.presentation.screens.home.HomeScreen
import com.sebastijanzindl.galore.presentation.screens.library.LibraryScreen
import com.sebastijanzindl.galore.presentation.screens.search.SearchScreen
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@OptIn(ExperimentalLayoutApi::class)
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
        fun navigateToCocktailDetails(cocktailId: String) {
            navController.navigate(AppScreen.Main.CocktailDetails.route.replace(
                oldValue = "{cocktail-id}",
                newValue = cocktailId
            ))
        }

        fun navigateToGeneratedCocktailDetails(cocktailId: String, shouldPopBackstack: Boolean = false) {
            if(shouldPopBackstack) {
                navController.navigate(AppScreen.Main.GeneratedCocktailDetails.route.replace(
                    oldValue = "{cocktail-id}",
                    newValue = cocktailId
                    ),
                ) {
                    popUpTo(AppScreen.Main.GenerateSelectFlavours.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(AppScreen.Main.GeneratedCocktailDetails.route.replace(
                    oldValue = "{cocktail-id}",
                    newValue = cocktailId
                ))
            }

        }

        fun navigateToSelectFlavourScreen() {
            navController.navigate(AppScreen.Main.GenerateSelectFlavours.route);
        }
        fun navigateToGenerateSelectCocktailsScreen() {
            navController.navigate(AppScreen.Main.GenerateSelectCocktails.route)
        }

        fun navigateToGenerateLoadingScreen() {
            navController.navigate(AppScreen.Main.GenerateLoading.route) {
                popUpTo(AppScreen.Main.GenerateSelectFlavours.route) {
                    inclusive = true
                }
            };
        }
        fun navigateBack() {
            navController.navigateUp()
        }



        composable(
            route = AppScreen.Main.Home.route,
            enterTransition = { scaleIn() + fadeIn(tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            HomeScreen(
                modifier = Modifier.padding(paddingValues),
                sharedSectionViewModel = sharedSectionViewModel,
                navigateToCocktailSection = { title -> navigateToCocktailSection(title) },
                singleCocktailCardPress = { id -> navigateToCocktailDetails(id) }
            )
        }
        composable(
            route = AppScreen.Main.Search.route,
            enterTransition = { scaleIn() + fadeIn(tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            SearchScreen(
               modifier = Modifier
                   .padding(paddingValues)
                   .fillMaxSize(),
                navigateToCocktail = {id -> navigateToCocktailDetails(id)}
            )
        }
        composable(
            route = AppScreen.Main.Generate.route,
            enterTransition = { scaleIn() + fadeIn(tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            GenerateCocktailScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                navigateToSelectFlavourScreen = { navigateToSelectFlavourScreen() }
            )
        }
        /**
         * Generate Cocktail Form
         */
        composable(
            route = AppScreen.Main.GenerateSelectFlavours.route,
            enterTransition = { scaleIn() + fadeIn(tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedGenerateCocktailViewModel: GenerateCocktailViewModel = it.sharedViewModel<GenerateCocktailViewModel>(navController = navController)
            GenerateSelectFlavoursScreen(
                modifier =  Modifier.padding(paddingValues),
                sharedGenerateCocktailViewModel = sharedGenerateCocktailViewModel,
                navigateToSelectCocktailsScreen = { navigateToGenerateSelectCocktailsScreen() },
                goBack = { navigateBack() }
            )
        }
        composable(
            route = AppScreen.Main.GenerateSelectCocktails.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedGenerateCocktailViewModel: GenerateCocktailViewModel = it.sharedViewModel<GenerateCocktailViewModel>(navController = navController)
            GenerateSelectCocktailsScreen(
                modifier = Modifier.padding(paddingValues),
                sharedGenerateCocktailViewModel = sharedGenerateCocktailViewModel,
                navigateToGenerateLoadingScreen = { navigateToGenerateLoadingScreen() },
                goBack = { navigateBack() }
            )
        }
        composable(
            route = AppScreen.Main.GenerateLoading.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedGenerateCocktailViewModel: GenerateCocktailViewModel = it.sharedViewModel<GenerateCocktailViewModel>(navController = navController)
            GenerateLoadingScreen(
                modifier = Modifier.padding(paddingValues),
                sharedGenerateCocktailViewModel = sharedGenerateCocktailViewModel,
                navigateToGenerateCocktailDetailsScreen = { id -> navigateToGeneratedCocktailDetails(id, true) },
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
                navigateToCocktailSection = { title -> navigateToCocktailSection(title) },
                singleCocktailCardPress = { id -> navigateToCocktailDetails(id)},
                generatedCocktailCardPress = { id -> navigateToGeneratedCocktailDetails(id)},
            )
        }
        composable(
            route = AppScreen.Main.CocktailSection.route,
            enterTransition = { scaleIn() + fadeIn(tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val sharedSectionViewModel: SectionSharedViewModel = it.sharedViewModel<SectionSharedViewModel>(navController = navController);
            CocktailSectionScreen(
                modifier = Modifier.padding(paddingValues),
                sharedSectionViewModel = sharedSectionViewModel,
                singleCocktailCardPress = { id ->  navigateToCocktailDetails(id)},
                generatedCocktailCardPress = { id -> navigateToGeneratedCocktailDetails(id)}
            )
        }
        composable(
            route = AppScreen.Main.CocktailDetails.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(700)) }
        ) {
            val cocktailId = it.arguments?.getString("cocktail-id")!!;
            CocktailDetailsScreen(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                cocktailId = cocktailId,
            )
        }
        composable (
            route = AppScreen.Main.GeneratedCocktailDetails.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val cocktailId = it.arguments?.getString("cocktail-id")!!;
            GeneratedCocktailDetailsScreen(
                modifier = Modifier
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                cocktailId = cocktailId,
            )
        }
    }
}