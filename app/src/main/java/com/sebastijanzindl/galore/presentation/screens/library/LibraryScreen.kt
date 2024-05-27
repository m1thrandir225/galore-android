package com.sebastijanzindl.galore.presentation.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.CocktailCard
import com.sebastijanzindl.galore.presentation.component.CocktailCardInfo
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryScreenViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading.collectAsState();
    val cocktails by viewModel.userMadeCocktails.collectAsState();

    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Column (modifier = modifier.fillMaxSize()) {
            Text(text = cocktails?.count().toString());
            LazyColumn(
                modifier.fillMaxSize()
            ){
                if(cocktails != null) {
                    items(cocktails!!) { cocktail ->
                        if(cocktail != null) {
                            CocktailCard(
                                cocktail = CocktailCardInfo("https://radscxhkzslbyeslnepm.supabase.co/storage/v1/object/public/generated_cocktail_images/${cocktail.image}", cocktail.name),
                                onHeartPress = { /*TODO*/ },
                                onCardPress = { /*TODO*/ },
                                isFavourite = false
                            )
                        }

                    }
                }

            }
        }

    }
}