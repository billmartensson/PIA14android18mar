package se.magictechnology.pia14android18mar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class TodoRoute {
    TODOLIST,
    DETAIL,
    PROFILE,
    PROFILESETTINGS,
    TEST
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun TodoNavBar() {

    val navController = rememberNavController()
    var selectedNavigationIndex by remember { mutableIntStateOf(0) }

    val navigationItems = listOf(
        NavigationItem(
            title = "Todo",
            icon = Icons.Default.Home,
            route = TodoRoute.TODOLIST.name
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = TodoRoute.PROFILE.name
        ),
        NavigationItem(
            title = "Test",
            icon = Icons.Default.Person,
            route = TodoRoute.TEST.name
        )

    )


    Scaffold(
        topBar = {
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Cyan,
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex == index,
                        onClick = {
                            selectedNavigationIndex = index
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                item.title,
                                color = if (index == selectedNavigationIndex)
                                    Color.Black
                                else
                                    Color.Gray
                            )
                        },
                        /*
                            selectedIconColor = MaterialTheme.colorScheme.surface,
                            indicatorColor = MaterialTheme.colorScheme.primary
                         */
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Yellow,
                            indicatorColor = Color.Red,
                            unselectedIconColor = Color.Green
                        )

                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startDestination = TodoRoute.TODOLIST.name,
            modifier = Modifier.padding(contentPadding)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun TodoNavBarPreview() {
    TodoNavBar()
}