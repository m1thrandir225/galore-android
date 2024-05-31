package com.sebastijanzindl.galore.presentation.screens.generatedCocktailDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    LaunchedEffect(cocktailId) {
            viewModel.getCocktail(cocktailId)
    }
    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Column (modifier = modifier.fillMaxSize().padding(80.dp)) {
            Text(text = cocktail?.name ?: "" )
        }
    }
}

