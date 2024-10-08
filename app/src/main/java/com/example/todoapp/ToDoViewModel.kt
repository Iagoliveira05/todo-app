package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {
    private val _todoList = MutableLiveData<List<ToDoModel>>()

    val toDoList: LiveData<List<ToDoModel>> = _todoList


    fun getAllToDo() {
        _todoList.value = ToDoManager.getAllToDo().reversed()
    }

    fun addToDo(title: String) {
        ToDoManager.addToDo(title)
        getAllToDo()
    }

    fun deleteToDo(id: Int) {
        ToDoManager.deleteToDo(id)
        getAllToDo()
    }

    fun onCheckedClick(toDo: ToDoModel) {
        ToDoManager.onCheckedClick(toDo)
        getAllToDo()
    }

    fun getCountIsCompleted(): Int {
        return ToDoManager.getCountIsCompleted()
    }

    fun getCountIsNotCompleted(): Int {
        return ToDoManager.getCountIsNotCompleted()
    }

    fun getCountAll(): Int {
        return ToDoManager.getCountAll()
    }


}
