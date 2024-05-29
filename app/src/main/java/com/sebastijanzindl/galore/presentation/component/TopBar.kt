package com.sebastijanzindl.galore.presentation.component

import android.os.Bundle
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.sebastijanzindl.galore.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehaviour: TopAppBarScrollBehavior,
    openBottomSheet: () -> Unit,
    navigateBack: () -> Unit,
    currentRoute: String?,
    arguments: Bundle?
) {
    when (currentRoute) {
        AppScreen.Settings.SettingsOverview.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title =  stringResource(AppScreen.Settings.SettingsOverview.titleResourceID)
            )
        }
        AppScreen.Settings.AccountSettings.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title =  stringResource(AppScreen.Settings.AccountSettings.titleResourceID)
            )
        }
        AppScreen.Settings.NotificationSettings.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title =  stringResource(AppScreen.Settings.NotificationSettings.titleResourceID)
            )
        }
        AppScreen.Settings.PasswordAndSecurity.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title = stringResource(id = AppScreen.Settings.PasswordAndSecurity.titleResourceID)
            )
        }
        AppScreen.Settings.PrivacyPolicy.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title = stringResource(id = AppScreen.Settings.PrivacyPolicy.titleResourceID)
            )
        }
        AppScreen.Settings.TermsAndConditions.route -> {
            SettingsGraphContent(
                scrollBehaviour = scrollBehaviour,
                navigateBack = navigateBack,
                title = stringResource(id = AppScreen.Settings.TermsAndConditions.titleResourceID)
            )
        }
        AppScreen.Main.Home.route,
        AppScreen.Main.Search.route,
        AppScreen.Main.Library.route,
        AppScreen.Main.Generate.route,
        AppScreen.Main.CocktailDetails.route -> {
            MainGraphContent(
                scrollBehaviour = scrollBehaviour,
                openBottomSheet = openBottomSheet
            )
        }
        AppScreen.Main.CocktailSection.route -> {
            val title = arguments?.getString("section-title")!!;
            CocktailSectionGraphContent(
                navigateBack = navigateBack,
                scrollBehaviour = scrollBehaviour,
                title = title
            )
        }
        else -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CocktailSectionGraphContent(
    navigateBack: () -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior,
    title: String?,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
                Text(text = title ?: "")
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = "Go Back Icon"
                )
            }
        },
        scrollBehavior = scrollBehaviour
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SettingsGraphContent(
    navigateBack: () -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior,
    title: String,
) {
    TopAppBar(
        modifier =  Modifier,
        title = {
            Text(text = title)
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