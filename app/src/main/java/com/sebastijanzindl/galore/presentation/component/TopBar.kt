package com.sebastijanzindl.galore.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.sebastijanzindl.galore.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehaviour: TopAppBarScrollBehavior,
    openBottomSheet: () -> Unit,
    navigateBack: () -> Unit,
    currentRoute: String?
) {
    if (currentRoute != null) {
        Log.d("current route", currentRoute)
    };
    if(currentRoute == AppScreen.Settings.SettingsOverview.route) {
        SettingsGraphContent(
            scrollBehaviour=scrollBehaviour,
            navigateBack = navigateBack
        )
    } else  {
        MainGraphContent(
            scrollBehaviour = scrollBehaviour,
            openBottomSheet = openBottomSheet
        )
    }
}



@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SettingsGraphContent(
    navigateBack: () -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier =  Modifier,
        title = {
            Text(text = "Settings")
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back Icon"
                )
            }
        },
        scrollBehavior = scrollBehaviour
    )
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainGraphContent(
    openBottomSheet: () -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    TopAppBar(title = {
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Logo(fontSize = 24.sp)
            IconButton(onClick = { openBottomSheet() }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Account")
            }
        }
    }, scrollBehavior = scrollBehaviour)
}