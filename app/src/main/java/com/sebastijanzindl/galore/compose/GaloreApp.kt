package com.sebastijanzindl.galore.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
        }
    }
}