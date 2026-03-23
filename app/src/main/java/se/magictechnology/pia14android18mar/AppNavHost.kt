package se.magictechnology.pia14android18mar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    todovm : TodoViewModel = viewModel(),
    navController: NavHostController,
    startDestination: String
    ) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // TODO
        navigation(route = TodoRoute.TODOMAIN.name, startDestination = TodoRoute.TODOLIST.name) {
            composable(TodoRoute.TODOLIST.name) {
                TodoList(todovm = todovm, goDetail = {
                    navController.navigate(TodoRoute.DETAIL.name)
                })
            }

            composable(TodoRoute.DETAIL.name) {
                Text("DETAIL")
            }
        }

        // PROFILE
        navigation(route = TodoRoute.PROFILEMAIN.name, startDestination = TodoRoute.PROFILE.name) {
            composable(TodoRoute.PROFILE.name) {
                Profile(todovm = todovm)
            }
            composable(TodoRoute.PROFILESETTINGS.name) {
                Text("PROFILE SETTINGS")
            }
        }


        // FAVORITES
        composable(TodoRoute.FAVORITES.name) {
            Text("FAVORITE SCREEN")
        }
    }
}