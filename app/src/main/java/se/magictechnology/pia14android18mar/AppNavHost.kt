package se.magictechnology.pia14android18mar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
        composable(TodoRoute.TODOLIST.name) {
            TodoList(todovm = todovm, goDetail = {
                navController.navigate(TodoRoute.DETAIL.name)
            })
        }
        composable(TodoRoute.DETAIL.name) {
            Text("DETAIL")
        }


        // PROFILE
        composable(TodoRoute.PROFILE.name) {
            Profile(todovm = todovm)
        }
        composable(TodoRoute.PROFILESETTINGS.name) {
            Text("PROFILE SETTINGS")
        }

        // TEST
        composable(TodoRoute.TEST.name) {
            Text("TEST TEST TEST")
        }
    }
}