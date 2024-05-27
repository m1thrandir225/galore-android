package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.CocktailIngredient
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailTagSection(
    modifier: Modifier = Modifier,
    cocktails: List<Cocktail>,
    tagName: String,
    cocktailCardType: CocktailCardType = CocktailCardType.Vertical,
    canNavigateToSection: Boolean = false,
    navigateToSection: () -> Unit,
    ) {
    val pagerState = rememberPagerState(pageCount = {
        cocktails.count()
    })

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )

    val favourites = remember {
        mutableListOf<Cocktail>()
    }


    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tagName,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if(!canNavigateToSection) {
                IconButton(onClick = navigateToSection ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "arrow right")
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(cocktailCardType.value),
            pageSpacing = 24.dp,
            flingBehavior = fling,
            contentPadding = PaddingValues(horizontal = 24.dp)

        ) { page ->
            CocktailCard(
                cocktail = CocktailCardInfo(cocktails[page].image, cocktails[page].name),
                onHeartPress = {
                               if(favourites.contains(cocktails[page])) {
                                   favourites.remove(cocktails[page])
                               } else {
                                   favourites.add(cocktails[page])
                               }
                },
                onCardPress = { /*TODO*/ },
                isFavourite = favourites.contains(cocktails[page]),
                cardType = cocktailCardType,
            )
        }
    }
}


@Preview
@Composable
private fun CocktailTagSectionPreview() {
    GaloreTheme {
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
        CocktailTagSection(cocktails = cocktails, tagName = "Preview Section", cocktailCardType = CocktailCardType.Horizontal, navigateToSection = {})
    }
}