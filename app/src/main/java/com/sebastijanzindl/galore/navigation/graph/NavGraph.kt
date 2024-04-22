package com.sebastijanzindl.galore.navigation.graph

sealed class NavGraph(val route: String) {
    data object Auth: NavGraph("auth");
    data object Onboarding: NavGraph("onboarding");
    data object Main: NavGraph("main");

    data object Splash: NavGraph("loading")
    data object Settings: NavGraph("settings")
}