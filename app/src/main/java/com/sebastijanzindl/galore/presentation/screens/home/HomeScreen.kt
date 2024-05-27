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
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.CocktailIngredient
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel
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
    userProfileSharedViewModel: ProfileSharedViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope();

    val cocktails = listOf(
        Cocktail(
            id = "2",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            ingredients = listOf(
                CocktailIngredient(
                    ingredient = "Glass",
                    amount = "1",
                ),
                CocktailIngredient(
                    ingredient = "Gin",
                    amount = "1"
                )
            ),
            name = "Gin & Tonic",
            instructions = listOf("Put gin into the glass", "Drink it")
        ),
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            ingredients = listOf(
                CocktailIngredient(
                    ingredient = "Glass",
                    amount = "1",
                ),
                CocktailIngredient(
                    ingredient = "Gin",
                    amount = "1"
                )
            ),
            name = "Gin & Tonic",
            instructions = listOf("Put gin into the glass", "Drink it")
        ),
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


