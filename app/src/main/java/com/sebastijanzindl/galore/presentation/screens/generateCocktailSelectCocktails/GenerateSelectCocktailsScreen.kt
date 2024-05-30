package com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectCocktails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel

@Composable
fun GenerateSelectCocktailsScreen(
    modifier: Modifier = Modifier,
    viewModel: GenerateSelectCocktailsViewModel = hiltViewModel(),
    sharedGenerateCocktailViewModel: GenerateCocktailViewModel,
    navigateToGenerateLoadingScreen: () -> Unit,
    goBack: () -> Unit,
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Previous")
            }
            Button(onClick = navigateToGenerateLoadingScreen ) {
                Text(text = "Next")
            }
        }
    }
}
