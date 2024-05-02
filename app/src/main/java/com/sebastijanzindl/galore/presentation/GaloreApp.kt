package com.sebastijanzindl.galore.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import io.github.jan.supabase.gotrue.SessionStatus

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState();

    val corutineScrope = rememberCoroutineScope();

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

    val bottomBarState = rememberSaveable() {
        mutableStateOf(true);
        
    }
    val topBarState = rememberSaveable {
        mutableStateOf(true);
    }

    navBackStackEntry?.destination?.route?.let { Log.e("CURRENT SCREEN:", it) };
    when(navBackStackEntry?.destination?.route) {
        AppScreen.Auth.Login.route -> {
            bottomBarState.value = false;
            topBarState.value = false;
        }
        AppScreen.Auth.Register.route -> {
            bottomBarState.value = false;
            topBarState.value = false;
        }
        AppScreen.Auth.Welcome.route -> {
            bottomBarState.value = false;
            topBarState.value = false;
        }
        //TODO: find more elegant solution then a when expression
        else -> {
            bottomBarState.value = true;
            topBarState.value = true;
        }
    }
    val sessionStatus = viewModel.sessionStatus.collectAsState();

    val isAuthenticated = when(sessionStatus.value) {
        is SessionStatus.Authenticated -> true;
        else -> false;
    }

    Scaffold (
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            if(bottomBarState.value) {
                BottomNavigationBar(navController = navController)
            }
        }
    ){paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            RootNavHost(
                navHostController = navController,
                isAuthenticated = isAuthenticated
            );
        }
    }
}

