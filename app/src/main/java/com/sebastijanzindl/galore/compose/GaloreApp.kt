package com.sebastijanzindl.galore.compose

import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sebastijanzindl.galore.BuildConfig
import com.sebastijanzindl.galore.compose.screens.home.HomeScreen
import com.sebastijanzindl.galore.compose.screens.login.LoginScreen
import com.sebastijanzindl.galore.compose.navigation.Screen
import com.sebastijanzindl.galore.compose.navigation.authNavGraph
import com.sebastijanzindl.galore.compose.navigation.mainNavGraph
import com.sebastijanzindl.galore.compose.navigation.splashNavGraph
import com.sebastijanzindl.galore.compose.screens.register.RegisterScreen
import com.sebastijanzindl.galore.compose.screens.welcome.WelcomeScreen
import com.sebastijanzindl.galore.viewmodels.LoginScreenViewModel
import com.sebastijanzindl.galore.viewmodels.MainViewModel
import com.sebastijanzindl.galore.viewmodels.RegisterScreenViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth

@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackEntry?.destination?.route

    val corutineScrope = rememberCoroutineScope();

    val snackbarHostState = remember {
        SnackbarHostState()
    }


    GaloreNavHost(
        isLoggedIn = viewModel.isLoggedIn,
        navController = navController
    )
}

@Composable
fun GaloreNavHost (
    isLoggedIn: Boolean,
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)

    NavHost(navController = navController, startDestination = "loading") {
        splashNavGraph(navController = navController)
        mainNavGraph(navController =  navController)
        authNavGraph(navController = navController)

    }
}
