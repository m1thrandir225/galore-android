package com.sebastijanzindl.galore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.presentation.screens.login.LoginScreen
import com.sebastijanzindl.galore.presentation.screens.register.RegisterScreen
import com.sebastijanzindl.galore.presentation.screens.welcome.WelcomeScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Auth.Welcome.route,
        route = AppScreen.Auth.route
    ) {
        composable(
            route = AppScreen.Auth.Welcome.route
        ) {
            WelcomeScreen(
                navigateToRegister = {
                    navController.navigate(AppScreen.Auth.Register.route)
                }
            )
        }
        composable(
            route = AppScreen.Auth.Login.route
        ) {
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(AppScreen.Auth.Register.route) {
                        popUpTo(AppScreen.Auth.Welcome.route)
                    }
                },
                navigateToMain = {
                    navController.navigate(AppScreen.Main.Home.route) {
                        popUpTo(AppScreen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToOnboarding = {
                    navController.navigate(AppScreen.Onboarding.FeatureShowcase.route) {
                        popUpTo(AppScreen.Auth.route) {
                            inclusive = true;
                        }
                    }
                }
            )

        }
        composable(
            route = AppScreen.Auth.Register.route
        ) {
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(AppScreen.Auth.Login.route) {
                        popUpTo(AppScreen.Auth.Welcome.route)
                    }

                },
                paddingValues = paddingValues,
                navigateToOnboarding = {
                    navController.navigate(AppScreen.Onboarding.FeatureShowcase.route) {
                        popUpTo(AppScreen.Auth.route) {
                            inclusive = true;
                        }
                    }
                }
            )
        }
    }
}