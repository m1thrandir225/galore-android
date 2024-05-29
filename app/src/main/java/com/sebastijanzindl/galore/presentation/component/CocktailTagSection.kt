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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailTagSection(
    modifier: Modifier = Modifier,
    cocktails: List<CocktailCardInfo>,
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
        if(pagerState.pageCount > 0) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(cocktailCardType.value),
                pageSpacing = 24.dp,
                flingBehavior = fling,
                contentPadding = PaddingValues(horizontal = 24.dp)

            ) { page ->
                CocktailCard(
                    cocktail = cocktails[page],
                    onCardPress = { /*TODO*/ },
                    cardType = cocktailCardType,
                )
            }
        } else {
          Text(
              modifier = Modifier.padding(horizontal = 24.dp),
              text = "There are no cocktails in this section",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface
          )
        }
    }
}


@Preview
@Composable
private fun CocktailTagSectionPreview() {
    GaloreTheme {
        val cocktails = listOf(
            CocktailCardInfo(
                id = "1",
                name = "Gin & Tonic",
                image = "https://plus.unsplash.com/premium_photo-1671647122910-3fa8ab4990cb?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            ),
            CocktailCardInfo(
                id = "2",
                image = "https://images.unsplash.com/photo-1609951651556-5334e2706168?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                name = "Margarita",

            ),
            CocktailCardInfo(
                id = "3",
                name = "Nokishta711",
                image = "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            )
        )

        CocktailTagSection(cocktails = cocktails, tagName = "Preview Section", cocktailCardType = CocktailCardType.Horizontal, navigateToSection = {})
    }
}