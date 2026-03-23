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


    private var _favitems = MutableStateFlow(listOf<Todoitem>())
    val favitems: StateFlow<List<Todoitem>> = _favitems.asStateFlow()


    fun loadtodo() {

        _todoitems.value = listOf()

        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            val todos = tododao.getTodolist()

            _todoitems.value = todos
        }
    }

    fun loadfav() {

        _favitems.value = listOf()

        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            val todos = tododao.getFavorites()

            _favitems.value = todos
        }
    }

    fun addtodo(todo : Todoitem) {
        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            tododao.addTodo(todo)
            loadtodo()
        }
    }

    fun savetodo(todo : Todoitem) {
        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            tododao.updateTodo(todo)
            loadtodo()
        }
    }

    fun makefavorite(todo : Todoitem) {
        val newtodo = todo.copy(favorite = true)
        newtodo.uid = 0

        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            tododao.addTodo(newtodo)
            loadtodo()
        }
    }

    fun addFavToTodo(todo : Todoitem) {
        val newtodo = todo.copy(favorite = false)
        newtodo.uid = 0
        newtodo.done = false


        CoroutineScope(Dispatchers.IO).launch {
            val tododao = db.todoitemdao()
            tododao.addTodo(newtodo)
            loadtodo()
        }
    }
}