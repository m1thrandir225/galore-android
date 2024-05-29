package com.sebastijanzindl.galore.presentation.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.domain.models.Section
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryScreenViewModel = hiltViewModel(),
    sharedSectionViewModel: SectionSharedViewModel = hiltViewModel(),
    navigateToCocktailSection: (title: String) -> Unit,
) {
    val isLoading by viewModel.isLoading.collectAsState();
    val cocktails by viewModel.userMadeCocktails.collectAsState();
    val likedCocktails by viewModel.userLikedCocktails.collectAsState();

    val customizedCocktails: List<CocktailCardInfo> = cocktails.map {
        CocktailCardInfo(
            id = it.id,
            image = "https://radscxhkzslbyeslnepm.supabase.co/storage/v1/object/public/generated_cocktail_images/${it.image}",
            name = it.name
        )
    }
    val customizedLikedCocktails: List<CocktailCardInfo> = likedCocktails.map {
        CocktailCardInfo(
            id = it.id,
            image = it.image,
            name = it.name
        )
    }

    val sections: List<Section> = listOf(
        Section(
            cocktails = customizedLikedCocktails,
            tagName = "Your Favourites"
        ),
        Section(
            cocktails = customizedCocktails,
            tagName = "Your Generated Cocktails"
        )
    )

    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(sections) {
                CocktailTagSection(
                    cocktails = it.cocktails,
                    tagName = it.tagName,
                    cocktailCardType = CocktailCardType.Vertical,
                    navigateToSection = {
                        sharedSectionViewModel.addSectionData(it.tagName, it.cocktails)
                        navigateToCocktailSection(it.tagName)
                    })
            }
        }
    }
}