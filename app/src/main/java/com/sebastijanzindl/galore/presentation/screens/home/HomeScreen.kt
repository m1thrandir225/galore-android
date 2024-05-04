package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import kotlinx.datetime.LocalDate

data class Section(
    val cocktails: List<Cocktail>,
    val tagName: String,
    val isFeatured: Boolean = false,
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope();

    val sheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true )

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val openBottomSheet = {
        showBottomSheet = true
    }

    val dismissBottomSheet = {
        showBottomSheet = false
    }

    val cocktails = listOf(
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1609951651556-5334e2706168?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = ""
        ),
        Cocktail(
            id = "2",
            image = "https://plus.unsplash.com/premium_photo-1687354207716-b74e8c056def?q=80&w=3088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Rum & Coke",
            name = "Rum & Coke",
            steps = ""
        ),
        Cocktail(
            id = "3",
            image = "https://images.unsplash.com/photo-1551538827-9c037cb4f32a?q=80&w=2761&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Mojito",
            name = "Mojito",
            steps = ""
        ),
        Cocktail(
            id = "4",
            image = "https://images.unsplash.com/photo-1587223962930-cb7f31384c19?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Martini",
            name = "Martini",
            steps = ""
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


