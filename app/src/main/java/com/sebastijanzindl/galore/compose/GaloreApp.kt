package com.sebastijanzindl.galore.compose

import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sebastijanzindl.galore.compose.home.HomeScreen
import com.sebastijanzindl.galore.compose.login.LoginScreen
import com.sebastijanzindl.galore.compose.register.RegisterScreen
import com.sebastijanzindl.galore.compose.welcome.WelcomeScreen

@Composable
fun GaloreApp() {
    val navController = rememberNavController()
    GaloreNavHost(
        navController
    )
}

@Composable
fun GaloreNavHost (
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)

    NavHost(navController = navController, startDestination = "auth") {
        navigation(startDestination = Screen.Welcome.route, route = "auth") {
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
                enterTransition = {
                    return@composable fadeIn(tween(500))
                },
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
                RegisterScreen(onLoginClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Welcome.route)
                    }
                })
            }
            composable(
                route = Screen.Login.route,
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
                    onRegisterClick = {
                        navController.navigate(Screen.Register.route) {
                            popUpTo(Screen.Welcome.route)
                        }
                    }
                )
            }
        }

        navigation(startDestination = Screen.Home.route, route = "app") {
            composable(route = Screen.Home.route) {
                HomeScreen()
            }
        }

    }
}
