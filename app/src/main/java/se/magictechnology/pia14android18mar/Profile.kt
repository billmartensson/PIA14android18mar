package se.magictechnology.pia14android18mar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Profile(todovm : TodoViewModel = viewModel()) {

    val mynumber = todovm.mynumber.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text("THIS IS PROFILE")

        Text("CLICK: ${mynumber.value}")

        Button(onClick = {
            todovm.addTonumber()
        }) {
            Text("CLICK HERE!!!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile()
}