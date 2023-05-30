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

@RestController
class TodoController {
    @GetMapping(value = ["/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun readTodo(@RequestParam("id") id: String): ResponseEntity<TodoObject?> {
        val uid = UUID.fromString(id)

        val todo = readTodoObject(uid)
        return if(todo != null) {
            ResponseEntity.ok(todo)
        } else {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
        }
    }

    @PostMapping(value = ["/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createTodo(@RequestBody todoObject: TodoObject): ResponseEntity<TodoObject> {
        val todo = createTodoObject(todoObject)

        return ResponseEntity.status(HttpStatus.CREATED).body(todo)
    }

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