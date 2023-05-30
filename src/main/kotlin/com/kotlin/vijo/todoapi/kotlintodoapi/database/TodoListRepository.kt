package com.kotlin.vijo.todoapi.kotlintodoapi.database

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoListObject
import java.util.*

val todoLists: MutableCollection<TodoListObject> = mutableListOf()

fun readTodoListObject(id: UUID): TodoListObject? {
    for(todoList in todoLists) {
        if(todoList.id == id) {
            return todoList
        }
    }
    return null
}

fun createTodoListObject(todoListObject: TodoListObject): TodoListObject {
    val todoList = TodoListObject(title = todoListObject.title, todos = todoListObject.todos)
    todoLists.add(todoList)
    return todoList
}

fun updateTodoListObject(todoListObject: TodoListObject): TodoListObject? {
    for(todoList in todoLists) {
        if(todoList.id == todoListObject.id) {
            todoLists.remove(todoList)
            todoLists.add(todoListObject)
            return todoListObject
        }
    }
    return null
}