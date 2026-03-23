package se.magictechnology.pia14android18mar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import se.magictechnology.pia14android18mar.ui.theme.Nicered
import se.magictechnology.pia14android18mar.ui.theme.PIA14android18marTheme
import kotlin.collections.get

@Composable
fun TodoList(todovm : TodoViewModel = viewModel(), goDetail: (gotodo : Todoitem) -> Unit = {}) {

    val todos = todovm.todoitems.collectAsState()

    LaunchedEffect(true) {
        todovm.loadtodo()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            val newtodo = Todoitem(uid = 0, todotitle = "TEST", done = false)
            todovm.addtodo(newtodo)
        }) {
            Text("ADD")
        }

        LazyColumn() {
            items(todos.value.size) {
                Row(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .clickable {
                        //todovm.deletetodo(todos.value[it])
                        //todovm.changedone(todos.value[it])
                        goDetail(todos.value[it])
                    }
                ) {
                    Text(todos.value[it].todotitle)

                    if(todos.value[it].done) {
                        Text("DONE")
                    } else {
                        Text("NOT DONE")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    PIA14android18marTheme {
        TodoList()
    }
}


/*

Text(
    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
    text = "TODOLIST",
    fontSize = 50.sp,
    color = MaterialTheme.colorScheme.primary
)

Text(text = "CLICK: ${mynumber.value}", fontSize = 50.sp)

Button(onClick = {
    todovm.addTonumber()
}) {
    Text("CLICK HERE!!!")
}

Button(onClick = {
    goDetail()
}) {
    Text("GO DETAIL")
}

 */