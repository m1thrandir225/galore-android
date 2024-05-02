package com.sebastijanzindl.galore.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToFavouriteCocktails: () -> Unit,
    navigateToGenerateCocktails: () -> Unit,
    currentRoute: NavDestination?
) {
    Column (
    ) {
        Text(text = "Search")
    }
}