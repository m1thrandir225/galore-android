package com.sebastijanzindl.galore.compose.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()){
    //Authentication - first time user
    data object Welcome: Screen("welcome");
    data object Register: Screen("register");

    data object Login: Screen("login");

    data object Onboarding: Screen("onboarding");

    data object EnablePushNotifications: Screen("enable-push-notifications");

    //Main or App - Continuing User
    data object Home: Screen("home")

    data object Cocktail: Screen("/cocktail/")

    data object Search: Screen("/search?q=")

}