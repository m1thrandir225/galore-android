package com.sebastijanzindl.galore.presentation.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.domain.models.Section
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryScreenViewModel = hiltViewModel(),
    sharedSectionViewModel: SectionSharedViewModel = hiltViewModel(),
    navigateToCocktailSection: (title: String) -> Unit,
    singleCocktailCardPress: (cocktailId: String) -> Unit,
    generatedCocktailCardPress: (cocktailId: String) -> Unit,
) {
    val isLoading by viewModel.isLoading.collectAsState();
    val cocktails by viewModel.userMadeCocktails.collectAsState();
    val likedCocktails by viewModel.userLikedCocktails.collectAsState();
    val isRefreshing by viewModel.isRefreshing.collectAsState();

    val refreshState = rememberPullToRefreshState()

    LaunchedEffect(isRefreshing) {
        if(isRefreshing) {
            refreshState.startRefresh()
        } else {
            refreshState.endRefresh()
        }
    }

    if(refreshState.isRefreshing) {
        LaunchedEffect (true) {
            viewModel.refreshData()
        }
    }


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
            tagName = "Your Generated Cocktails",
            generatedCocktailsSection = true
        )
    )
    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Box(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(refreshState.nestedScrollConnection)
        ) {
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
                            sharedSectionViewModel.addSectionData(
                                it.tagName,
                                it.cocktails,
                                it.generatedCocktailsSection
                            )
                            navigateToCocktailSection(it.tagName)
                        },
                        cardPress = { cocktailId ->
                            if(it.generatedCocktailsSection) {
                                generatedCocktailCardPress(cocktailId)
                            } else {
                                singleCocktailCardPress(cocktailId)
                            }
                        }
                    )
                }
            }
            PullToRefreshContainer(state = refreshState, modifier = Modifier.align(Alignment.TopCenter))
        }

    }
}