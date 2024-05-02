package com.sebastijanzindl.galore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.allSet.AllSetScreen
import com.sebastijanzindl.galore.presentation.screens.favouriteFlavours.FavouriteFlavoursScreen
import com.sebastijanzindl.galore.presentation.screens.featureShowcase.FeatureShowcaseScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = AppScreen.Onboarding.FeatureShowcase.route,
        route = AppScreen.Onboarding.route
    ) {
        composable(
            route = AppScreen.Onboarding.FeatureShowcase.route
        ) {
            FeatureShowcaseScreen(
                navigateToPushNotificationScreen = {
                    navController.navigate(AppScreen.Onboarding.EnablePushNotifications.route)
                }
            )
        }
        composable(
            route = AppScreen.Onboarding.SetupFavouriteFlavours.route
        ) {
            FavouriteFlavoursScreen(
                navigateToAllSet = {
                    navController.navigate(AppScreen.Onboarding.AllSet.route) {
                        popUpTo(AppScreen.Onboarding.SetupFavouriteFlavours.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
        composable(
            route = AppScreen.Onboarding.AllSet.route
        ) {
            AllSetScreen(
                navigateToMain = {
                    navController.navigate(AppScreen.Main.Home.route) {
                        popUpTo(AppScreen.Onboarding.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
    }
}