package com.sebastijanzindl.galore.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
)

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    NavigationBar {
        items.forEachIndexed { index, screen ->
            val isSelected = currentRoute?.hierarchy?.any { it.route ==  screen.route } == true;

            NavigationBarItem(selected = isSelected,
                onClick = {
                          navController.navigate(screen.route);
                },
                icon = {
                    Icon(imageVector = if(isSelected) { screen.selectedIcon } else { screen.unselectedIcon },
                        contentDescription = screen.title)
                })
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    GaloreTheme {
        val items = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = "home"
            ),
            BottomNavigationItem(
                title = "Search",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
                route = "search"
            ),
            BottomNavigationItem(
                title = "Home",
                selectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.sparkles_filled),
                unselectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.sparkles),
                route = "home"
            ),
            BottomNavigationItem(
                title = "Home",
                selectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.book_filled),
                unselectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.book_24px),
                route = "home"
            )
        )

        val navController = rememberNavController();

        BottomNavigationBar(navController = navController, items = items);
    }
}