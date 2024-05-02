package com.sebastijanzindl.galore.presentation.screens.favouriteCocktails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

@Composable
fun FavouriteCocktailsScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToGenerateCocktails: () -> Unit,
    currentRoute: NavDestination?
) {
    Column (
    ) {
        Text(text = "Favourite Cocktails")
    }
}