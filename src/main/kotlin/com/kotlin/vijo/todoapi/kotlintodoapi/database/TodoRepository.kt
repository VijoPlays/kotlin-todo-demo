package com.kotlin.vijo.todoapi.kotlintodoapi.database

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoObject
import java.util.*

/**
 * The InMemoryDatabase of TodoObjects.
 */
val todos: MutableCollection<TodoObject> = mutableListOf()

/**
 * Attempts to retrieve a TodoObject from the database.
 */
fun readTodoObject(id: UUID): TodoObject? {
    for(todo in todos) {
        if(todo.id == id) {
            return todo
        }
    }
    return null
}

/**
 * Creates a TodoObject in the database.
 */
fun createTodoObject(todoObject: TodoObject): TodoObject {
    val todo = TodoObject(title = todoObject.title, done = todoObject.done)
    todos.add(todo)
    return todo
}

/**
 * Updates a TodoObject in the database.
 */
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