package com.sebastijanzindl.galore.compose.navigation

sealed class NavGraph(val route: String) {
    data object Auth: NavGraph("auth");
    data object Onboarding: NavGraph("onboarding");
    data object Main: NavGraph("main");

    data object Splash: NavGraph("loading")

}