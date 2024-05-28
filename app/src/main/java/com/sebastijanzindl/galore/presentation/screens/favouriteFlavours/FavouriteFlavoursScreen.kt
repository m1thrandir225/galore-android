package com.sebastijanzindl.galore.presentation.screens.favouriteFlavours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.presentation.component.FlavourButton
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@Composable
fun FavouriteFlavoursScreen(
    modifier: Modifier = Modifier,
    navigateToAllSet: () -> Unit,
) {
    val flavours: List<String> = listOf(
        "Sweet", "Bitter", "Savoury", "Sour",
        "Spicy", "Furity", "Smokey", "Herbaceous"
    )

    val userFavouriteFlavours = remember {
        mutableStateListOf("");
    }

    fun favouriteFlavour (flavourName: String ) {
        if(userFavouriteFlavours.contains(flavourName)) {
            userFavouriteFlavours.remove(flavourName)
        } else {
            userFavouriteFlavours.add(flavourName)
        }
    }

    Scaffold (
        modifier = modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()

            Text(
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = "Choose your favourite flavours"
            )
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 24.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(flavours) {flavour ->
                    FlavourButton(
                        onClick = { favouriteFlavour(flavour) },
                        buttonText = flavour,
                        isInList = userFavouriteFlavours.contains(flavour)
                   )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = {
                    navigateToAllSet()
                }
            ) {
                Text(text = "Continue")
            }
        }
    }
}




@Preview(apiLevel = 33)
@Composable
private fun ButtonPreview() {
    GaloreTheme {
        FlavourButton( onClick =  {}, buttonText = "Spicy", isInList = false)
    }
}



@Preview(apiLevel = 33)
@Composable
private fun Preview1() {
    GaloreTheme {
        FavouriteFlavoursScreen(
            navigateToAllSet = {}
        )
    }
}