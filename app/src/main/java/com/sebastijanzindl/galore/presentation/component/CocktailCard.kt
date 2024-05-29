package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.domain.models.CocktailIngredient
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

enum class CocktailCardType(val value: Dp) {
    Horizontal(280.dp),
    Vertical(180.dp)
}

@Composable
fun CocktailCard (
    modifier: Modifier = Modifier,
    cardType: CocktailCardType = CocktailCardType.Vertical,
    cocktail: CocktailCardInfo,
    onCardPress: () -> Unit,
    cocktailIsGenerated: Boolean = false

) {

    OutlinedCard(
        onClick = onCardPress,
        modifier = modifier.width(cardType.value).fillMaxHeight()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth()
                .height(166.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(cocktail.image)
                .crossfade(true)
                .build(),
            contentDescription = "Image of the cocktail: ${cocktail.name}",
            placeholder = painterResource(id = R.drawable.cocktail_card_image_preview), //TODO: Replace with actually placeholder
            contentScale = ContentScale.Crop,
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = cocktail.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}

@Preview
@Composable
private fun VerticalCocktailCardPreview() {
    var isFavourite by remember {
        mutableStateOf(false)
    }

    val changeIsFavourite = {
        isFavourite = !isFavourite
    }
    GaloreTheme {
        val cocktail = Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
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
        )
        CocktailCard(
            cocktail = CocktailCardInfo(
                id = cocktail.id,
                image = cocktail.image,
                name = cocktail.name
            ),
            onCardPress = {},
        )

    }
}

@Preview
@Composable
private fun HorizontalCocktailCardPreview() {
    var isFavourite by remember {
        mutableStateOf(false)
    }

    val changeIsFavourite = {
        isFavourite = !isFavourite
    }
    GaloreTheme {
        val cocktail = Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
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
        )
        CocktailCard(
            cardType = CocktailCardType.Horizontal,
            cocktail = CocktailCardInfo(
                id = cocktail.id,
                image = cocktail.image,
                name = cocktail.name
            ),
            onCardPress = {},
        )

    }
}