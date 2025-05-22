package com.example.odev7.data.repo

import com.example.odev7.data.ToDoDatabaseHelper
import com.example.odev7.data.entity.ToDo
import javax.inject.Inject


class ToDoRepository @Inject constructor(
    private val dbHelper: ToDoDatabaseHelper
) {
    fun insertToDo(toDo: ToDo) = dbHelper.insertToDo(toDo)
    fun updateToDo(toDo: ToDo) = dbHelper.updateToDo(toDo)
    fun deleteToDo(toDo: ToDo) = dbHelper.deleteToDo(toDo)
    fun getAllToDos(): List<ToDo> = dbHelper.getAllToDos()
}
