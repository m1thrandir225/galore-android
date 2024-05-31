package com.sebastijanzindl.galore.presentation.screens.generatedCocktailDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner

@Composable
fun GeneratedCocktailDetailsScreen(
    modifier: Modifier,
    cocktailId: String,
    viewModel: GeneratedCocktailDetailsScreenViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val cocktail by viewModel.cocktail.collectAsState()

    LaunchedEffect(Unit) {
        println(cocktailId)
        viewModel.getCocktail(cocktailId)
    }
    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Column (modifier = modifier
            .fillMaxSize()) {
            Text(text = "Hello World")
            cocktail?.name?.let { Text(text = it) }

        }
    }
}

