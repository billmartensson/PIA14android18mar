package se.magictechnology.pia14android18mar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoList(goDetail: () -> Unit = {}) {

    var mynumber by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("TODOLIST")

        Text("CLICK: $mynumber")

        Button(onClick = {
            mynumber++
        }) {
            Text("CLICK HERE!!!")
        }

        Button(onClick = {
            goDetail()
        }) {
            Text("GO DETAIL")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    TodoList()
}