package com.kotlin.vijo.todoapi.kotlintodoapi.database

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoListObject
import java.util.*

/**
 * The InMemoryDatabase of TodoLists.
 */
val todoLists: MutableCollection<TodoListObject> = mutableListOf()

/**
 * Attempts to retrieve a TodoList from the database.
 */
fun readTodoListObject(id: UUID): TodoListObject? {
    for(todoList in todoLists) {
        if(todoList.id == id) {
            return todoList
        }
    }
    return null
}

/**
 * Creates a TodoList in the database.
 */
fun createTodoListObject(todoListObject: TodoListObject): TodoListObject {
    val todoList = TodoListObject(title = todoListObject.title, todos = todoListObject.todos)
    todoLists.add(todoList)
    return todoList
}

/**
 * Updates a TodoList in the database.
 */
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