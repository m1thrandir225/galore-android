package com.sebastijanzindl.galore.compose

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()){
    data object Home: Screen("home")

    data object Welcome: Screen("welcome");

    data object Register: Screen("register");
}