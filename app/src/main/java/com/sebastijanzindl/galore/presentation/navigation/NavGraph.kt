package com.sebastijanzindl.galore.presentation.navigation

sealed class NavGraph(val route: String) {
    data object Auth: NavGraph("auth");
    data object Onboarding: NavGraph("onboarding");
    data object Main: NavGraph("main");

    data object Splash: NavGraph("loading")

}