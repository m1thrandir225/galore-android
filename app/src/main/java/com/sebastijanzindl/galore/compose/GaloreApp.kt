package com.sebastijanzindl.galore.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.compose.home.HomeScreen
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

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                onGettingStartedClick = {
                    navController.navigate(
                        Screen.Register.route
                    )
                }
            )
        }
        composable(route = Screen.Register.route) {
            RegisterScreen()
        }
    }
}
