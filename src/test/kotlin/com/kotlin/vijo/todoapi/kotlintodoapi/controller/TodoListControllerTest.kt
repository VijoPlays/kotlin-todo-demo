package com.kotlin.vijo.todoapi.kotlintodoapi.controller

import com.kotlin.vijo.todoapi.kotlintodoapi.model.TodoListObject
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

@SpringBootTest
class TodoListControllerTest {

    private val todoListController = TodoListController()

    @BeforeEach
    fun setUp() {
    }

    //Get Tests
    @Test
    fun get_Existent_TodoListID_Returns_OK() {
        //Arrange
        val todoList = TodoListObject(title = "someListTitle")
        val createdList = todoListController.createTodoList(todoList)

        //Act
        val res = todoListController.readTodoList(createdList.body!!.id.toString())

        //Assert
        Assertions.assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.OK).body(todoList));
    }

    @Test
    fun get_Non_Existent_TodoListID_Returns_NoContent() {
        //Arrange

        //Act
        val res = todoListController.readTodoList(UUID.randomUUID().toString())

        //Assert
        Assertions.assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    @Test
    fun get_Invalid_TodoListID_Returns_BadRequest() {
        //Arrange

        //Act
        val res = todoListController.readTodoList("someInvalidID")

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, res.statusCode)
    }

    //Create Tests
    @Test
    fun create_TodoList_Returns_Created() {
        //Arrange
        val todoList = TodoListObject(title = "someListTitle")

        //Act
        val res = todoListController.createTodoList(todoList)

        //Assert
        Assertions.assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todoList));
    }

    //Update Tests
    @Test
    fun update_Existent_Todo_Returns_Created() {
        //Arrange
        val todoList = TodoListObject(title = "someListTitle")
        val createdTodoList = todoListController.createTodoList(todoList)
        val updatedTodoList = createdTodoList.body!!
        updatedTodoList.title = "newTitle"

        //Act
        val res = todoListController.updateTodoList(updatedTodoList)

        //Assert
        Assertions.assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(updatedTodoList));
    }

    @Test
    fun update_Non_Existent_TodoList_Returns_NoContent() {
        //Arrange

        //Act
        val res = todoListController.updateTodoList(TodoListObject(title = "someTitle"))

        //Assert
        Assertions.assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }
}