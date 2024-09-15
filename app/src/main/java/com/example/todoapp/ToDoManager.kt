package com.example.todoapp

import androidx.lifecycle.MutableLiveData

object ToDoManager {
    private val _todoList = mutableListOf<ToDoModel>()


    fun getAllToDo(): List<ToDoModel> {
        return _todoList
    }

    fun addToDo(title: String) {
        _todoList.add(ToDoModel(System.currentTimeMillis().toInt(), title))
    }

    fun deleteToDo(id: Int) {
        _todoList.removeIf {
            id == it.id
        }
    }

}