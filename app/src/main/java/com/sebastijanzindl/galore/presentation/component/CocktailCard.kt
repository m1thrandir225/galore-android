package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate

@Composable
fun  CocktailCard (
    cocktail: Cocktail,
    onHeartPress: () -> Unit,
    onCardPress: () -> Unit
) {
    OutlinedCard(
        onClick = onCardPress,

    ) {
        Image(painter = painterResource(id = R.drawable.google_logo), contentDescription = "")
        Text(text = "Hello World");
        Text(text = "Hello!")

    }
}


@Preview
@Composable
private fun CocktailCardPreview() {
    GaloreTheme {
        val cocktail = Cocktail(
            id = "1",
            image = "",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        )
        CocktailCard(
            cocktail = cocktail,
            onCardPress = {},
            onHeartPress = {},
        )
    }
}