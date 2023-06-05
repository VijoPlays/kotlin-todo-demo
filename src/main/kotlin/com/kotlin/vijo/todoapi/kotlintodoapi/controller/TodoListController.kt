package com.kotlin.vijo.todoapi.kotlintodoapi.controller

import com.kotlin.vijo.todoapi.kotlintodoapi.database.createTodoListObject
import com.kotlin.vijo.todoapi.kotlintodoapi.database.readTodoListObject
import com.kotlin.vijo.todoapi.kotlintodoapi.database.updateTodoListObject
import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoListObject
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Controller class for TodoLists.
 */
@RestController
class TodoListController {
    /**
     * Attempts to retrieve a TodoList from the database, based on the provided ID.
     */
    @GetMapping(value = ["/todoList"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun readTodoList(@RequestParam("id") id: String): ResponseEntity<Any> {
        return try {
            val uid = UUID.fromString(id)

            val todoList = readTodoListObject(uid)

            if(todoList != null) {
                ResponseEntity.ok(todoList)
            } else {
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
            }
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided ID was not of UUID format!")
        }

    }

    /**
     * Creates a TodoList based on the provided parameters.
     */
    @PostMapping(value = ["/todoList"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createTodoList(@RequestBody todoListObject: TodoListObject): ResponseEntity<TodoListObject> {
        val todoList = createTodoListObject(todoListObject)

        return ResponseEntity.status(HttpStatus.CREATED).body(todoList)
    }

    /**
     * Updates a TodoList based on the provided parameters.
     */
    @PatchMapping(value = ["/todoList"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodoList(@RequestBody todoListObject: TodoListObject): ResponseEntity<TodoListObject?> {
        val todoList = updateTodoListObject(todoListObject)

        return if(todoList != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(todoList)
        } else {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
        }
    }
}