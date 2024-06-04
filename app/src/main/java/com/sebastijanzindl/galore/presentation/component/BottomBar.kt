package com.sebastijanzindl.galore.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.navigation.AppScreen
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    val onClick: () -> Unit,
)

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            title = "Home",
            route = AppScreen.Main.Home.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            onClick = {
                val currentRoute = navController.currentDestination?.route
                if(currentRoute != AppScreen.Main.Home.route) {
                    navController.navigate(AppScreen.Main.Home.route)
                }

            },
        ),
        BottomNavigationItem(
            title = "Search",
            route = AppScreen.Main.Search.route,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon =  Icons.Outlined.Search,
            onClick = {
                val currentRoute = navController.currentDestination?.route
                if(currentRoute != AppScreen.Main.Search.route) {
                    navController.navigate(AppScreen.Main.Search.route)
                }

            }
        ),
        BottomNavigationItem(
            title = "Generate",
            route = AppScreen.Main.Generate.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.sparkles_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.sparkles),
            onClick = {
                val currentRoute = navController.currentDestination?.route
                if(currentRoute != AppScreen.Main.Generate.route) {
                    navController.navigate(AppScreen.Main.Generate.route)
                }
            }
        ),
        BottomNavigationItem(
            title = "Library",
            route = AppScreen.Main.Library.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.book_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.book_24px),
            onClick = {
                val currentRoute = navController.currentDestination?.route
                if(currentRoute != AppScreen.Main.Library.route) {
                    navController.navigate(AppScreen.Main.Library.route)

                }

            }
        )
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination

        items.forEachIndexed { index, screen ->
            val isSelected = currentRoute?.hierarchy?.any { it.route ==  screen.route } == true;
            NavigationBarItem(
                selected = isSelected,
                label = {
                        Text(text = screen.title)
                },
                onClick = screen.onClick,
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
        BottomNavigationBar(navController = rememberNavController());
    }
}