package com.sebastijanzindl.galore.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.navigation.AppScreen
import com.sebastijanzindl.galore.navigation.RootNavHost
import com.sebastijanzindl.galore.presentation.component.BottomNavigationBar
import com.sebastijanzindl.galore.presentation.viewmodels.MainViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val hasNotificationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    viewModel.setHasEnabledNotifications(
        hasNotificationPermission
    )

    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val topBarState = rememberSaveable {
        mutableStateOf(true)
    }

    when(navBackStackEntry?.destination?.route) {
        AppScreen.Auth.Login.route -> {
            bottomBarState.value = false
            topBarState.value = false
        }
        AppScreen.Auth.Register.route -> {
            bottomBarState.value = false
            topBarState.value = false
        }
        AppScreen.Auth.Welcome.route -> {
            bottomBarState.value = false
            topBarState.value = false
        }
        AppScreen.SplashScreen.route -> {
            bottomBarState.value = false
            topBarState.value = false
        }
        //TODO: find more elegant solution then a when expression
        else -> {
            bottomBarState.value = true
            topBarState.value = true
        }
    }


    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(bottomBarState.value) {
                BottomNavigationBar(navController = navController)
            }
        }
    ){paddingValues ->
        RootNavHost(
            navHostController = navController,
            paddingValues
        )
    }
}

