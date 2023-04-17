package com.srinivas.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srinivas.todo.data.TodoLocalRepository
import com.srinivas.todo.ui.screens.TodoViewModel

class TodoViewModelFactory(private val todoRepository: TodoLocalRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(todoRepository) as T
    }
}