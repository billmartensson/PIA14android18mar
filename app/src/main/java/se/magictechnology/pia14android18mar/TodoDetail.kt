package se.magictechnology.pia14android18mar

import android.R.attr.icon
import android.window.BackEvent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoDetail(todovm : TodoViewModel = viewModel(), currenttodo : Todoitem, goBack : () -> Unit = {}) {

    var todoname by remember { mutableStateOf(currenttodo.todotitle) }

    var showAlert by remember { mutableStateOf(false) }

    BackHandler(enabled = true, onBack = {
        if(currenttodo.todotitle == todoname) {
            goBack()
        } else {
            showAlert = true
        }
    })


    Column(modifier = Modifier.fillMaxSize()) {
        Text("TODO DETAIL")

        TextField(value = todoname, onValueChange = {
            todoname = it
        })

        Button(onClick = {
            currenttodo.todotitle = todoname
            todovm.savetodo(currenttodo)
            goBack()
        }) {
            Text("Save")
        }

        if(showAlert) {
            AlertDialog(
                icon = {
                },
                title = {
                    Text(text = "Unsaved changes")
                },
                text = {
                    Text(text = "Are you sure you want to not save?")
                },
                onDismissRequest = {

                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            goBack()
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TodoDetailPreview() {
    TodoDetail(currenttodo = Todoitem(uid = 0, todotitle = "TEST", done = false))
}