package com.sebastijanzindl.galore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.settings.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Settings.SettingsOverview.route,
        route = AppScreen.Settings.route
    ) {
        val goBackCallback = {
            navController.popBackStack()
        }
        val navigateToNotifications = {
            navController.navigate(AppScreen.Settings.NotificationSettings.route)
        }

        val navigateToPasswordsAndSecurity = {
            navController.navigate(AppScreen.Settings.PasswordAndSecurity.route)
        }
        val navigateToAccountSettings = {
            navController.navigate(AppScreen.Settings.AccountSettings.route)
        }
        composable(
            route = AppScreen.Settings.SettingsOverview.route
        ) {
            SettingsScreen(
                navigateBack = { goBackCallback()},
                navigateToAccountSettings = navigateToAccountSettings,
                navigateToPasswordsAndSecurity =  navigateToPasswordsAndSecurity,
                navigateToNotifications = navigateToAccountSettings
            )
        }

        composable(
            route = AppScreen.Settings.AccountSettings.route
        ) {
           Text(text = "wow") 
        }
        
        composable(
            route = AppScreen.Settings.NotificationSettings.route
        ) {
            Text(text = "Notificaiton Settings")
        }
        
        composable(
            route = AppScreen.Settings.PasswordAndSecurity.route
        ) {
            Text(text = "Password & Security")
        }
    }
}