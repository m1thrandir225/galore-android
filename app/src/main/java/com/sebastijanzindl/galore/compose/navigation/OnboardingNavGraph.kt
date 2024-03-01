package com.sebastijanzindl.galore.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.home.HomeScreen
import com.sebastijanzindl.galore.compose.screens.onboarding.OnboardingScreen

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
            OnboardingScreen(
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
            HomeScreen(navigateToAuth = {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(NavGraph.Main.route) {
                        inclusive = true
                    }
                }
            })
        }
    }
}