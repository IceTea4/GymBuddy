package org.aj.gymbuddy.ui.compose

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBottomBar(navController: NavController) {
    NavigationBar (
        modifier = Modifier.height(100.dp)
    ) {
        NavigationItem(
            navController = navController,
            route = "workouts",
            icon = Icons.Default.Menu,
        )

        NavigationItem(
            navController = navController,
            route = "home",
            icon = Icons.Filled.DateRange,
        )

        NavigationItem(
            navController = navController,
            route = "settings",
            icon = Icons.Filled.Settings,
        )
    }
}

@Composable
private fun RowScope.NavigationItem(navController: NavController, route: String, icon: ImageVector) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBarItem(
        selected = currentRoute == route,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = route
            )
        },
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    )
}
