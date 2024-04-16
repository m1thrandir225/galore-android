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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.Tag
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailTagSection(
    modifier: Modifier = Modifier,
    cocktails: List<Cocktail>,
    tagName: String,
    navigateToSection: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        cocktails.count()
    });
    val selectedItem =  remember {
        derivedStateOf { pagerState.currentPage }
    }

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )

    val favourites = remember {
        mutableListOf<Cocktail>();
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
            IconButton(onClick = navigateToSection ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "arrow right")
            }

        }


        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(CocktailCardType.Vertical.value),
            pageSpacing = 24.dp,
            flingBehavior = fling,
            contentPadding = PaddingValues(horizontal = 24.dp)

        ) { page ->
            CocktailCard(
                cocktail = cocktails[selectedItem.value],
                onHeartPress = {
                               if(favourites.contains(cocktails[selectedItem.value])) {
                                   favourites.remove(cocktails[selectedItem.value])
                               } else {
                                   favourites.add(cocktails[selectedItem.value])
                               }
                },
                onCardPress = { /*TODO*/ },
                isFavourite = favourites.contains(cocktails[selectedItem.value])
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
                id = "1",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Gin, Tonic",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            ),
            Cocktail(
                id = "2",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Coke & Rum",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            ),
            Cocktail(
                id = "1",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Gin, Tonic",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            ),
            Cocktail(
                id = "2",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Coke & Rum",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            ),
            Cocktail(
                id = "1",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Gin, Tonic",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            ),
            Cocktail(
                id = "2",
                image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = LocalDate.parse("2024-02-24").toString(),
                embeddingVector = listOf(0.10, 1.22, 1.55),
                ingredients = "Coke & Rum",
                name = "Gin & Tonic",
                steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
            )

        )
        CocktailTagSection(cocktails = cocktails, tagName = "Preview Section", navigateToSection = {})
    }
}