package com.sebastijanzindl.galore.presentation.screens.favouriteCocktails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavouriteCocktailsScreen(
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
    ) {
        Text(text = "Favourite Cocktails")
    }
}