package com.sebastijanzindl.galore.navigation.screen

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()){
    /**
     * Splash Screen
     */
    data object Splash: Screen("splash")
    /**
     * Authentication Nav Graph
     */
    data object Welcome: Screen("welcome");
    data object Register: Screen("register");
    data object Login: Screen("login");

    /**
     * Onboarding Nav Graph
     */
    data object Onboarding: Screen("feature-showcase");

    data object EnablePushNotifications: Screen("enable-push-notifications");

    data object FavouriteFlavours: Screen("favourite-flavours-setup");

    data object AllSet: Screen("all-set");

    /**
     * Main Graph
     */
    data object Home: Screen("home")

    data object Cocktail: Screen("/cocktail/")

    data object Search: Screen("/search?q=")

    /**
     * Settings Graph
     */
    data object SettingsOverview: Screen("settings-overview")

    data object AccountSettings: Screen("account-settings")

    data object PasswordAndSecurity: Screen("password-and-security")

    data object NotificationSettings: Screen("notification-settings")
}