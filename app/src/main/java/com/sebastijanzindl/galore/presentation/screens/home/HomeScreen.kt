package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    sharedSectionViewModel: SectionSharedViewModel = hiltViewModel(),
    navigateToCocktailSection: (title: String) -> Unit,
    singleCocktailCardPress: (cocktailId: String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope();

    val toastMessage by viewModel.toastMessage.collectAsState();
    val isLoading by viewModel.isLoading.collectAsState();

    val cocktailSections by viewModel.cocktailSections.collectAsState()

    SnackbarMessageHandler(snackbarMessage = toastMessage, onDismissSnackbar = { viewModel.dismissToastMessage() })

   if(isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingSpinner(shouldShow = isLoading)
        }
    } else {
        LazyColumn  (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(cocktailSections, key = { it.tagName }) { section ->
                val cocktailCardType = if(section.isFeatured) {
                    CocktailCardType.Horizontal
                } else {
                    CocktailCardType.Vertical
                }

                val isLast = section == cocktailSections.last();
                CocktailTagSection(
                    modifier = Modifier.padding(
                        bottom = when(isLast) {
                            true -> {
                                32.dp
                            }
                            else -> {
                                0.dp
                            }
                        }
                    ),
                    cocktails = section.cocktails,
                    tagName = section.tagName,
                    canNavigateToSection = section.isFeatured,
                    cocktailCardType = cocktailCardType ,
                    navigateToSection = {
                        sharedSectionViewModel.addSectionData(
                            cocktails = section.cocktails,
                            sectionName = section.tagName,
                            isGeneratedSection = section.generatedCocktailsSection
                        )
                        navigateToCocktailSection(section.tagName)
                    },
                    cardPress = {cocktailId ->
                        singleCocktailCardPress(cocktailId)
                    }
                )
            }
        }
    }
}


