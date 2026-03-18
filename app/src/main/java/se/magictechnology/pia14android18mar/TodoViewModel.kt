package se.magictechnology.pia14android18mar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel : ViewModel() {

    private var _mynumber = MutableStateFlow(0)
    val mynumber: StateFlow<Int> = _mynumber.asStateFlow()


    fun addTonumber() {
        _mynumber.value++

    }
}