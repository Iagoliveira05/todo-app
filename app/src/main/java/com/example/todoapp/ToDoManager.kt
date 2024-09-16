package com.example.todoapp

import androidx.lifecycle.MutableLiveData

object ToDoManager {
    private val _todoList = mutableListOf<ToDoModel>()

    fun getAllToDo(): List<ToDoModel> {
        return _todoList
    }

    fun addToDo(title: String) {
        _todoList.add(ToDoModel(System.currentTimeMillis().toInt(), title, false))
    }

    fun deleteToDo(id: Int) {
        _todoList.removeIf {
            id == it.id
        }
    }

    fun getCountIsCompleted(): Int {
        var count = 0
        _todoList.forEach {
            if (it.isCompleted) {
                count++
            }
        }
        return count
    }

    fun getCountIsNotCompleted(): Int {
        var count = 0
        _todoList.forEach {
            if (!it.isCompleted) {
                count++
            }
        }
        return count
    }

    fun onCheckedClick(toDo: ToDoModel) {
        _todoList[_todoList.indexOf(toDo)] = toDo.copy(
            isCompleted = !_todoList[_todoList.indexOf(toDo)].isCompleted
        )
    }

    fun getCountAll(): Int {
        return _todoList.size
    }
}