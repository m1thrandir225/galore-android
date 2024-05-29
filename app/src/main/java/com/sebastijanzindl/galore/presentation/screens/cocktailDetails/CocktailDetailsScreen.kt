package com.sebastijanzindl.galore.presentation.screens.cocktailDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CocktailDetailsScreen(
    modifier: Modifier = Modifier,
    cocktailId: String?
) {
    Column(
        modifier = modifier
    ) {
        if(cocktailId != null) {
            Text(text = "Cocktail Details")
            Text(text = cocktailId)   
        } else {
            Text(text = "There was a problem")
        }
    }
}