package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.allSet.AllSetScreen
import com.sebastijanzindl.galore.presentation.screens.enablePushNotifications.EnablePushNotificationScreen
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
            route = AppScreen.Onboarding.FeatureShowcase.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            FeatureShowcaseScreen(
                navigateToPushNotificationScreen = {
                    navController.navigate(AppScreen.Onboarding.EnablePushNotifications.route)
                },
                navigateToMain = {
                    navController.navigate(AppScreen.Main.Home.route) {
                        popUpTo(AppScreen.Onboarding.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
        composable(
            route = AppScreen.Onboarding.EnablePushNotifications.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            EnablePushNotificationScreen(
                navigateToFavouriteFlavours = {
                    navController.navigate(AppScreen.Onboarding.SetupFavouriteFlavours.route) {
                        popUpTo(AppScreen.Onboarding.EnablePushNotifications.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
        composable(
            route = AppScreen.Onboarding.SetupFavouriteFlavours.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
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
            route = AppScreen.Onboarding.AllSet.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(700)
                )
            }
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