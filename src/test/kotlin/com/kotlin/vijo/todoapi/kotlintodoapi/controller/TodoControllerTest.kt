package com.kotlin.vijo.todoapi.kotlintodoapi.controller

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

@SpringBootTest
class TodoControllerTest {

    private val todoController = TodoController()

    @BeforeEach
    fun setUp() {
    }

    //Get Tests
    @Test
    fun get_Existent_TodoID_Returns_OK() {
        //Arrange
        val todo = TodoObject(title = "someTitle")
        val createdTodo = todoController.createTodo(todo)

        //Act
        val res = todoController.readTodo(createdTodo.body!!.id.toString())

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.OK).body(todo));
    }

    @Test
    fun get_Non_Existent_TodoID_Returns_NoContent() {
        //Arrange

        //Act
        val res = todoController.readTodo(UUID.randomUUID().toString())

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    //Create Tests
    @Test
    fun create_Todo_Returns_Created() {
        //Arrange
        val todoObject = TodoObject(title = "someTitle")

        //Act
        val res = todoController.createTodo(todoObject)

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todoObject));
    }

    //Update Tests
    @Test
    fun update_Existent_Todo_Returns_Created() {
        //Arrange
        val todo = TodoObject(title = "someTitle")
        val createdTodo = todoController.createTodo(todo)
        val updatedTodo = createdTodo.body!!
        updatedTodo.done = true

        //Act
        val res = todoController.updateTodo(updatedTodo)

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(updatedTodo));
    }

    @Test
    fun update_Non_Existent_Todo_Returns_NoContent() {
        //Arrange

        //Act
        val res = todoController.updateTodo(TodoObject(title = "someTitle"))

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }
}