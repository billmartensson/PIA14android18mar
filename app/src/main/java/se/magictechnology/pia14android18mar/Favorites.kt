package se.magictechnology.pia14android18mar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Favorites(todovm : TodoViewModel = viewModel(), goDetail: (gotodo : Todoitem) -> Unit = {}) {
    val todos = todovm.favitems.collectAsState()

    LaunchedEffect(true) {
        todovm.loadfav()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(todos.value.size) {
                Row(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .clickable {
                        //todovm.deletetodo(todos.value[it])
                        //todovm.changedone(todos.value[it])
                        goDetail(todos.value[it])
                        //todovm.makefavorite(todos.value[it])
                        //todovm.addFavToTodo(todos.value[it])

                    }
                ) {
                    Text(todos.value[it].todotitle)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    Favorites()
}