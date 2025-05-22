package com.example.odev7.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odev7.data.repo.ToDoRepository
import com.example.odev7.data.entity.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _toDoList = MutableStateFlow<List<ToDo>>(emptyList())
    val toDoList: StateFlow<List<ToDo>> = _toDoList

    init {
        loadToDos()
    }

    fun loadToDos() {
        viewModelScope.launch {
            val list = repository.getAllToDos()
            _toDoList.value = list
        }
    }

    fun addToDo(name: String) {
        viewModelScope.launch {
            repository.insertToDo(ToDo(name = name))
            loadToDos() // veriyi güncelle
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            repository.updateToDo(toDo)
            loadToDos()
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            repository.deleteToDo(toDo)
            loadToDos()
        }
    }
}
