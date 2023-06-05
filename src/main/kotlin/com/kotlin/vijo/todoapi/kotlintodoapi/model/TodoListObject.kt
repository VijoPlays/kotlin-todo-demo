package com.kotlin.vijo.todoapi.kotlintodoapi.model

import java.util.*

/**
 * Class that represents a List/Group of TodoObjects.
 */
class TodoListObject(var title: String, var todos: List<UUID> = emptyList()) {
    val id = UUID.randomUUID()
}