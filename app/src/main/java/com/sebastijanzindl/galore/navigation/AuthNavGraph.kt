package com.sebastijanzindl.galore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.login.LoginScreen
import com.sebastijanzindl.galore.presentation.screens.register.RegisterScreen
import com.sebastijanzindl.galore.presentation.screens.welcome.WelcomeScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = AppScreen.Auth.Welcome.route,
        route = AppScreen.Auth.route
    ) {
        composable(
            route = AppScreen.Auth.Welcome.route
        ) {
            WelcomeScreen(
                onGettingStartedClick = {}
            )
        }
        composable(
            route = AppScreen.Auth.Login.route
        ) {
            LoginScreen(navigateToRegister = { /*TODO*/ }, navigateToMain = { /*TODO*/ }) {
            }
        }
        composable(
            route = AppScreen.Auth.Register.route
        ) {
            RegisterScreen(navigateToLogin = { /*TODO*/ }) {
                
            }
        }
    }
}