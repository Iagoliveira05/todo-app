package com.example.todoapp

data class ToDoModel (
    val id: Int,
    val toDo: String,
    var isCompleted: Boolean
)