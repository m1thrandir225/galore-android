package com.sebastijanzindl.galore.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.screens.login.LoginScreen
import com.sebastijanzindl.galore.presentation.screens.register.RegisterScreen
import com.sebastijanzindl.galore.presentation.screens.welcome.WelcomeScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.Welcome.route,
        route = NavGraph.Auth.route
    ) {
        /**
         * Welcome Screen - Initial Screen the user sees
         */
        composable(
            route = Screen.Welcome.route,
            enterTransition = {
                return@composable fadeIn(tween(500))
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(200)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(200)
                )
            },
        ) {
            WelcomeScreen(
                onGettingStartedClick = {
                    navController.navigate(
                        Screen.Register.route
                    )
                }
            )
        }
        /**
         * Register Screen - Screen for user registration
         */
        composable(
            route = Screen.Register.route,
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(200)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Welcome.route)
                    }
                },
                navigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(NavGraph.Auth.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }
        /**
         *Login Screen - Screen for users to login
         */
        composable(
            route = Screen.Login.route,
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(200)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Welcome.route)
                    }
                },
                navigateToOnboarding = {
                    navController.navigate(NavGraph.Onboarding.route) {
                        popUpTo(NavGraph.Auth.route) {
                            inclusive = true;
                        }
                    }
                },
                navigateToMain = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(NavGraph.Auth.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }



    }
}