package com.sebastijanzindl.galore.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.navigation.screen.Screen
import com.sebastijanzindl.galore.presentation.component.BottomNavigationBar
import com.sebastijanzindl.galore.presentation.component.BottomNavigationItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToFavouriteCocktails: () -> Unit,
    navigateToGenerateCocktails: () -> Unit,
    currentRoute: NavDestination?
) {
    val navigationItems: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            title = "Home",
            route = Screen.Home.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            onClick = navigateToHome,
        ),
        BottomNavigationItem(
            title = "Search",
            route = Screen.GenerateCocktail.route,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon =  Icons.Outlined.Search,
            onClick = {},
        ),
        BottomNavigationItem(
            title = "Generate",
            route = Screen.GenerateCocktail.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.sparkles_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.sparkles),
            onClick = navigateToGenerateCocktails
        ),
        BottomNavigationItem(
            title = "Library",
            route = Screen.Favourites.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.book_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.book_24px),
            onClick = navigateToFavouriteCocktails
        )
    )
    Scaffold (
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(currentRoute = currentRoute, items = navigationItems);
        }

    ) {
        Column (
            modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            Text(text = "Search")
        }
    }
}