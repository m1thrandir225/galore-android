package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.domain.models.Section
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    userProfileSharedViewModel: ProfileSharedViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope();

    val cocktails = listOf(
        CocktailCardInfo(
            name = "Gin & Tonic",
            image = "https://plus.unsplash.com/premium_photo-1671647122910-3fa8ab4990cb?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        CocktailCardInfo(
            image = "https://images.unsplash.com/photo-1609951651556-5334e2706168?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            name = "Margarita",

            ),
        CocktailCardInfo(
            name = "Nokishta711",
            image = "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        )
    )
    val sections = listOf(
        Section(
            cocktails = cocktails,
            tagName = "Today's Picks",
            isFeatured = true,
        ),
        Section(
            cocktails = cocktails,
            tagName = "After a long day"
        ),
        Section(
            cocktails = cocktails,
            tagName = "Something Exciting"
        ),
        Section(
            cocktails = cocktails,
            tagName = "To cool down"
        )
    )
    LazyColumn  (
        modifier = modifier
    ) {
        items(sections) { section ->
            val cocktailCardType = if(section.isFeatured) {
                CocktailCardType.Horizontal
            } else {
                CocktailCardType.Vertical
            }
            CocktailTagSection(cocktails = section.cocktails, tagName = section.tagName, canNavigateToSection = section.isFeatured, cocktailCardType = cocktailCardType , navigateToSection = {})
        }
    }
}


