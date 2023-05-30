package com.kotlin.vijo.todoapi.kotlintodoapi.model

import java.util.*

class TodoListObject(var title: String, var todos: List<UUID> = emptyList()) {
    val id = UUID.randomUUID()
}