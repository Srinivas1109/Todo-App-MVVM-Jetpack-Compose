package com.srinivas.todo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    fun getAllTodos() : Flow<List<Todo>>

    suspend fun toggleTodo(toggleValue: Int, id: Int)

    suspend fun updateTodo(todo: Todo)

}