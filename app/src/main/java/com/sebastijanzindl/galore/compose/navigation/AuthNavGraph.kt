package com.sebastijanzindl.galore.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.screens.home.HomeScreen
import com.sebastijanzindl.galore.compose.screens.login.LoginScreen
import com.sebastijanzindl.galore.compose.screens.onboarding.OnboardingScreen
import com.sebastijanzindl.galore.compose.screens.register.RegisterScreen
import com.sebastijanzindl.galore.compose.screens.welcome.WelcomeScreen
import com.sebastijanzindl.galore.compose.screens.login.LoginScreenViewModel
import com.sebastijanzindl.galore.viewmodels.RegisterScreenViewModel


fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.Welcome.route,route = "auth") {
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
            RegisterScreen(viewModel = hiltViewModel<RegisterScreenViewModel>(),
                onLoginClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Welcome.route)
                    }
                },
                navigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Onboarding.route)
                    }
                }
            )
        }
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
                viewModel = hiltViewModel<LoginScreenViewModel>(),
                onRegisterClick = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Welcome.route)
                    }
                }
            )
        }
        composable(
            route = Screen.Onboarding.route,
        ) {
            OnboardingScreen(navigateToPushNotificationScreen = {
                navController.navigate(Screen.EnablePushNotifications.route) {
                    popUpTo(Screen.EnablePushNotifications.route)
                }
            })
        }
        composable(
            route = Screen.EnablePushNotifications.route
        ) {
            HomeScreen(navigateToAuth = {
                navController.navigate(Screen.Welcome.route)
            })
        }
    }
}