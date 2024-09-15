package com.example.todoapp

data class ToDoModel (
    val id: Int,
    val toDo: String
)

fun getFakeToDo(): List<ToDoModel> {
    return listOf(
        ToDoModel(0, "dassdad"),
        ToDoModel(2, "sfd"),
        ToDoModel(450, "543"),
        ToDoModel(5640, "fsdhg"),
        ToDoModel(760, "khjk"),
        ToDoModel(7, "wqeq"),
    )

}