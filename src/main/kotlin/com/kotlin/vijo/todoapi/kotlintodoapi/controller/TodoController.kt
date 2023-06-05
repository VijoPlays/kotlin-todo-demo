package com.kotlin.vijo.todoapi.kotlintodoapi.controller

import com.kotlin.vijo.todoapi.kotlintodoapi.database.*
import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Controller class for TodoObjects.
 */
@RestController
class TodoController {
    /**
     * Attempts to retrieve a TodoObject from the database, based on the provided ID.
     */
    @GetMapping(value = ["/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun readTodo(@RequestParam("id") id: String): ResponseEntity<Any> {
        return try {
            val uid = UUID.fromString(id)

            val todo = readTodoObject(uid)
            if (todo != null) {
                ResponseEntity.ok(todo)
            } else {
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
            }
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided ID was not of UUID format!")
        }
    }

    /**
     * Creates a TodoObject based on the provided parameters.
     */
    @PostMapping(value = ["/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createTodo(@RequestBody todoObject: TodoObject): ResponseEntity<TodoObject> {
        val todo = createTodoObject(todoObject)

        return ResponseEntity.status(HttpStatus.CREATED).body(todo)
    }

    /**
     * Updates a TodoObject based on the provided parameters.
     */
    @PatchMapping(value = ["/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodo(@RequestBody todoObject: TodoObject): ResponseEntity<TodoObject?> {
        val todo = updateTodoObject(todoObject)

        return if(todo != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(todo)
        } else {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
        }
    }
}