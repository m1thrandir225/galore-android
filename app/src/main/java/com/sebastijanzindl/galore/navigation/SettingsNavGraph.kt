package com.sebastijanzindl.galore.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.accountSettings.AccountSettingScreen
import com.sebastijanzindl.galore.presentation.screens.notifications.NotificationSettings
import com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity.PasswordAndSecurityScreen
import com.sebastijanzindl.galore.presentation.screens.settings.SettingsOverviewScreen
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel

fun NavGraphBuilder.settingsNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Settings.SettingsOverview.route,
        route = AppScreen.Settings.route
    ) {
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
            route = AppScreen.Settings.SettingsOverview.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
        ) {
            val profileViewModel = it.sharedViewModel<ProfileSharedViewModel>(navController = navController)
            SettingsOverviewScreen(
                modifier = Modifier.padding(paddingValues),
                navigateToAccountSettings = navigateToAccountSettings,
                navigateToPasswordsAndSecurity =  navigateToPasswordsAndSecurity,
                navigateToNotifications =  navigateToNotifications
            )
        }
        composable(
            route = AppScreen.Settings.AccountSettings.route
        ) {
            val profileViewModel = it.sharedViewModel<ProfileSharedViewModel>(navController = navController)
           AccountSettingScreen(
               modifier = Modifier.padding(paddingValues),
           )
        }
        composable(
            route = AppScreen.Settings.PasswordAndSecurity.route
        ) {
            val profileViewModel = it.sharedViewModel<ProfileSharedViewModel>(navController = navController)
            PasswordAndSecurityScreen(
                modifier = Modifier.padding(paddingValues),
                navigateToAuth = {
                    navController.navigate(AppScreen.Auth.route) {
                        popUpTo(AppScreen.Main.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
        composable(
            route = AppScreen.Settings.NotificationSettings.route
        ) {
            val profileViewModel = it.sharedViewModel<ProfileSharedViewModel>(navController = navController)
            NotificationSettings(
                modifier = Modifier.padding(paddingValues),
                profileSharedViewModel = profileViewModel,
            )

        }
        

    }
}