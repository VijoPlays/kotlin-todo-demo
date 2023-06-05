package com.kotlin.vijo.todoapi.kotlintodoapi.model

import java.util.*

/**
 * Class that represents a Task that can be completed.
 */
class TodoObject(var title: String, var done: Boolean = false) {
    val id: UUID = UUID.randomUUID()
}