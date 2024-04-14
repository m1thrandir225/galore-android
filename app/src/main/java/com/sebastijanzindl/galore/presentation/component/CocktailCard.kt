package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate

@Composable
fun  CocktailCard (
    cocktail: Cocktail,
    onHeartPress: () -> Unit,
    onCardPress: () -> Unit,
    isFavourite: Boolean
) {

    OutlinedCard(
        onClick = onCardPress,
        modifier = Modifier.width(200.dp)
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
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            FilledIconButton(
                modifier = Modifier.size(32.dp),
                onClick = onHeartPress
            ) {
                if(isFavourite) {
                    Icon(Icons.Default.Favorite, contentDescription = "Add to Favourite", modifier = Modifier.padding(8.dp))
                } else {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Remove From Favourites", modifier = Modifier.padding(8.dp))
                }

            }
        }

    }
}

@Preview
@Composable
private fun CocktailCardPreview() {
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
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        )
        CocktailCard(
            cocktail = cocktail,
            onCardPress = {},
            onHeartPress = changeIsFavourite,
            isFavourite = isFavourite
        )
    }
}