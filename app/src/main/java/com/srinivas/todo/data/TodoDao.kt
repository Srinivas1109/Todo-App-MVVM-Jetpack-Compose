package com.srinivas.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM TODO")
    fun getAllTodos() : Flow<List<Todo>>

    @Query("UPDATE todo SET isDone = :toggleValue where id = :id")
    suspend fun toggleTodo(toggleValue: Int, id: Int)

    @Update
    suspend fun updateTodo(todo: Todo)

}