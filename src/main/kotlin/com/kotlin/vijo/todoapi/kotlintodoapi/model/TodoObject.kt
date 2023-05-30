package com.kotlin.vijo.todoapi.kotlintodoapi.model

import java.util.*

class TodoObject(var title: String, var done: Boolean = false) {
    val id: UUID = UUID.randomUUID()
}