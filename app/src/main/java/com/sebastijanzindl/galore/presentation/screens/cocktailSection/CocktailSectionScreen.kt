package com.sebastijanzindl.galore.presentation.screens.cocktailSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.CocktailCard
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@Composable
fun CocktailSectionScreen (
    modifier: Modifier = Modifier,
    sharedSectionViewModel: SectionSharedViewModel = hiltViewModel(),
    singleCocktailCardPress: (cocktailId: String) -> Unit
) {
    val sectionName by sharedSectionViewModel.sectionName.collectAsState();
    val cocktails by sharedSectionViewModel.cocktails.collectAsState();
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cocktails) { cocktail ->
            CocktailCard(
                cocktail = cocktail,
                onCardPress = {
                    singleCocktailCardPress(cocktail.id)
                },
                cardType = CocktailCardType.Vertical
            )
        }
    }
}