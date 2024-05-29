package com.sebastijanzindl.galore.presentation.screens.generatedCocktailDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GeneratedCocktailDetailsScreen(
    modifier: Modifier,
    cocktailId: String?,
    viewModel: GeneratedCocktailDetailsScreenViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val cocktail by viewModel.cocktail.collectAsState()

    LaunchedEffect(cocktailId) {
        if(cocktailId != null) {
            viewModel.getCocktail(cocktailId)
        }
    }
}

