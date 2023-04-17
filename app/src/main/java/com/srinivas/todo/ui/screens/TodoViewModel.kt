package com.srinivas.todo.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srinivas.todo.data.Todo
import com.srinivas.todo.data.TodoLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoLocalRepository) : ViewModel() {

    val todos = todoRepository.getAllTodos()

    var cachedTodo : Todo? = null

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }

    fun toggleTodo(toggleValue: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.toggleTodo(toggleValue, id)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }
}