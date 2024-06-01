package com.sebastijanzindl.galore.presentation.screens.cocktailDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sebastijanzindl.galore.domain.models.CocktailIngredient
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner

@Composable
fun CocktailDetailsScreen(
    modifier: Modifier = Modifier,
    cocktailId: String,
    viewModel: CocktailDetailsScreenViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState();
    val cocktail by viewModel.cocktail.collectAsState();
    val isFavourite by viewModel.isFavourite.collectAsState()

    LaunchedEffect(cocktailId) {
            viewModel.getCocktail(cocktailId)
    }

    if(isLoading) {
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingSpinner(shouldShow = isLoading)
        }
    } else {
        if(cocktail != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                ,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cocktail!!.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Cocktail image",
                    contentScale = ContentScale.Crop,
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cocktail!!.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    FilledIconButton(onClick = {
                        if(isFavourite) {
                            viewModel.removeCocktailFromFavourites(cocktailId)
                        } else {
                            viewModel.addCocktailToFavourites(cocktailId)
                        }
                    }) {
                        if(isFavourite) {
                            Icon(Icons.Default.Favorite, contentDescription = "Is Favourite")
                         } else {
                            Icon(Icons.Default.FavoriteBorder, contentDescription = "Is Favourite")

                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Glass",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Serve: ${cocktail!!.glass}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                IngredientsCarousel(cocktail!!.ingredients.count(), cocktail!!.ingredients)
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Instructions",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    cocktail!!.instructions.forEachIndexed { index, item ->
                        Text(
                            text = "${index+1}. $item",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun IngredientsCarousel(
    itemCount: Int,
    ingredients: List<CocktailIngredient>
) {
    val pagerState = rememberPagerState(pageCount = {itemCount})

    val fling = PagerDefaults.flingBehavior(state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )
    HorizontalPager(
        state = pagerState,
        pageSpacing = 24.dp,
        flingBehavior = fling,
        pageSize = PageSize.Fixed(200.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {ingredientIndex ->
        val currentIngredient = ingredients[ingredientIndex]
        Column (
            modifier = Modifier
                .width(200.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                //TODO: need a placeholder image until its loaded
                model =  ImageRequest.Builder(LocalContext.current)
                    .data("https://www.thecocktaildb.com/images/ingredients/${currentIngredient.ingredient}-Medium.png")
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = "Image for instruction: $ingredientIndex"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentIngredient.ingredient,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = currentIngredient.amount,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
            }

        }
    }
}