package com.sebastijanzindl.galore.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.screens.settings.SettingsScreen

fun NavGraphBuilder.settingsNavGraph (
    navController: NavController
) {
    navigation(startDestination = Screen.SettingsOverview.route, route = NavGraph.Settings.route) {
        composable(route = Screen.SettingsOverview.route) {
            SettingsScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToAccountSettings = {
                    navController.navigate(Screen.AccountSettings.route)
                },
                navigateToPasswordsAndSecurity = {
                    navController.navigate(Screen.PasswordAndSecurity.route)
                },
                navigateToNotifications = {
                    navController.navigate(Screen.NotificationSettings.route);
                }
            )
        }
    }

}