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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
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
    currentRoute: NavDestination?,
    items: List<BottomNavigationItem>
) {
    NavigationBar {
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
        val items = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = "home",
                onClick = {}
            ),
            BottomNavigationItem(
                title = "Search",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
                route = "search",
                onClick = {}
            ),
            BottomNavigationItem(
                title = "Generate",
                selectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.sparkles_filled),
                unselectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.sparkles),
                route = "home",
                onClick = {}
            ),
            BottomNavigationItem(
                title = "Library",
                selectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.book_filled),
                unselectedIcon = ImageVector.vectorResource(id = com.sebastijanzindl.galore.R.drawable.book_24px),
                route = "home",
                onClick = {}
            )
        )
        BottomNavigationBar(currentRoute = NavDestination("home"), items = items);
    }
}