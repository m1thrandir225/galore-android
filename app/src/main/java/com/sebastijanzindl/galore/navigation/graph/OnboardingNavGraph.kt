package com.sebastijanzindl.galore.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.screens.allSet.AllSetScreen
import com.sebastijanzindl.galore.presentation.screens.enablePushNotifications.EnablePushNotificationScreen
import com.sebastijanzindl.galore.presentation.screens.favouriteFlavours.FavouriteFlavoursScreen
import com.sebastijanzindl.galore.presentation.screens.featureShowcase.FeatureShowcaseScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.Onboarding.route,
        route = NavGraph.Onboarding.route
    ) {
        /**
         * Onboarding Screen - Showcase of basic features of the application
         */
        composable(
            route = Screen.Onboarding.route,
        ) {
            FeatureShowcaseScreen(
                navigateToPushNotificationScreen = {
                    navController.navigate(Screen.EnablePushNotifications.route) {
                        popUpTo(Screen.Onboarding.route) {
                            inclusive = true
                        }
                    }
            })
        }

        /**
         * EnablePushNotifications - Screen for the user to enable push notifications if he wants to
         */
        composable(
            route = Screen.EnablePushNotifications.route
        ) {
            EnablePushNotificationScreen(
                navigateToFavouriteFlavours = {
                    navController.navigate(Screen.FavouriteFlavours.route) {
                        popUpTo(Screen.EnablePushNotifications.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }

        /**
         * Favourite Flavours - Screen part of the onboarding process so we can determine what kind of
         * cocktail flavours the user wants
         */
        composable(
            route = Screen.FavouriteFlavours.route
        ) {
            FavouriteFlavoursScreen(
                navigateToAllSet = {
                    navController.navigate(Screen.AllSet.route) {
                        popUpTo(Screen.FavouriteFlavours.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }

        /**
         * All Set - Screen to show that the onboarding process is finished and the user can enter the application
         */
        composable(
            route = Screen.AllSet.route
        ) {
            AllSetScreen(
                navigateToMain = {
                    navController.navigate(NavGraph.Main.route) {
                        popUpTo(NavGraph.Onboarding.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }


    }
}