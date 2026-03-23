package se.magictechnology.pia14android18mar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private var _mynumber = MutableStateFlow(0)
    val mynumber: StateFlow<Int> = _mynumber.asStateFlow()

    fun addTonumber() {
        _mynumber.value++

    }


    lateinit var db : AppDatabase

    private var _todoitems = MutableStateFlow(listOf<Todoitem>())
    val todoitems: StateFlow<List<Todoitem>> = _todoitems.asStateFlow()


    fun loadtodo() {

        _todoitems.value = listOf()

        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            val todos = tododao.getAllTodo()

            _todoitems.value = todos
        }
    }

    fun addtodo(todo : Todoitem) {
        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            tododao.addTodo(todo)
            loadtodo()
        }
    }


}