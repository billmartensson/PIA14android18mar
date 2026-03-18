package se.magictechnology.pia14android18mar

import androidx.compose.foundation.layout.Column
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


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
            route = "todolist"
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = "profile"
        )
    )


    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex == index,
                        onClick = {
                            selectedNavigationIndex = index
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                item.title,
                                color = if(index == selectedNavigationIndex)
                                    Color.Black
                                else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.surface,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )

                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(navController, startDestination = "todolist") {
            composable("todolist") {
                TodoList()
            }
            composable("profile") {
                Text("PROFILE")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TodoNavBarPreview() {
    TodoNavBar()
}