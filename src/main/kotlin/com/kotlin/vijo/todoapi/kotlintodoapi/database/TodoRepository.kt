package com.kotlin.vijo.todoapi.kotlintodoapi.database

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoObject
import java.util.*

val todos: MutableCollection<TodoObject> = mutableListOf()

fun readTodoObject(id: UUID): TodoObject? {
    for(todo in todos) {
        if(todo.id == id) {
            return todo
        }
    }
    return null
}

fun createTodoObject(todoObject: TodoObject): TodoObject {
    val todo = TodoObject(title = todoObject.title, done = todoObject.done)
    todos.add(todo)
    return todo
}

fun updateTodoObject(todoObject: TodoObject): TodoObject? {
    for(todo in todos) {
        if(todo.id == todoObject.id) {
            todos.remove(todo)
            todos.add(todoObject)
            return todoObject
        }
    }
    return null
}