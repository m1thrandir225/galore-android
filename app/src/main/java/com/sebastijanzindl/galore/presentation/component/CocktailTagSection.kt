package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.Tag
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailTagSection(
    modifier: Modifier = Modifier,
    cocktails: List<Cocktail>,
    tagName: String
) {
    val pagerState = rememberPagerState(pageCount = {
        10
    });


    Column {
        Text(
            text = tagName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        HorizontalPager(state = pagerState) { page ->
            CocktailCard(
                cocktail = cocktails[page],
                onHeartPress = { /*TODO*/ },ea
                onCardPress = { /*TODO*/ },
                isFavourite = false
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
            )
        )
        CocktailTagSection(cocktails = cocktails, tagName = "Preview Section")
    }
}