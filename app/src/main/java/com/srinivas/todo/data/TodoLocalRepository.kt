package com.srinivas.todo.data

import kotlinx.coroutines.flow.Flow

class TodoLocalRepository(private val dao: TodoDao) : TodoRepository {
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos()
    }

    override suspend fun toggleTodo(toggleValue: Int, id: Int) {
        dao.toggleTodo(toggleValue, id)
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo = todo)
    }
}