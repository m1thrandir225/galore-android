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
import com.sebastijanzindl.galore.presentation.screens.forgotPassword.ForgotPasswordScreen
import com.sebastijanzindl.galore.presentation.screens.login.LoginScreen
import com.sebastijanzindl.galore.presentation.screens.register.RegisterScreen
import com.sebastijanzindl.galore.presentation.screens.welcome.WelcomeScreen
import com.sebastijanzindl.galore.presentation.viewmodels.AuthSharedViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    navigation(
        startDestination = AppScreen.Auth.Welcome.route,
        route = AppScreen.Auth.route
    ) {
        composable(
            route = AppScreen.Auth.Welcome.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
        ) {
            WelcomeScreen(
                navigateToRegister = {
                    navController.navigate(AppScreen.Auth.Register.route)
                }
            )
        }
        composable(
            route = AppScreen.Auth.Login.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            }
        ) {
            val sharedViewModel = it.sharedViewModel<AuthSharedViewModel>(navController = navController);
            LoginScreen(
                modifier = Modifier.padding(paddingValues),
                sharedViewModel = sharedViewModel,
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
                navigateToForgotPassword = {
                    navController.navigate(AppScreen.Auth.ForgotPassword.route) {
                        popUpTo(AppScreen.Auth.Login.route)
                    }
                }
            )

        }
        composable(
            route = AppScreen.Auth.Register.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            }
        ) {
            val sharedViewModel = it.sharedViewModel<AuthSharedViewModel>(navController = navController)
            RegisterScreen(
                modifier = Modifier.padding(paddingValues),
                sharedViewModel = sharedViewModel,
                navigateToLogin = {
                    navController.navigate(AppScreen.Auth.Login.route) {
                        popUpTo(AppScreen.Auth.Welcome.route)
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
            route = AppScreen.Auth.ForgotPassword.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            }
        ) {
            ForgotPasswordScreen(
                modifier = Modifier.padding(paddingValues),
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}