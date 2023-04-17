package com.srinivas.todo.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.srinivas.todo.data.Todo

@Composable
fun TodoList(
    todos: List<Todo>,
    modifier: Modifier = Modifier,
    showSnackbar: MutableState<Boolean>,
    onDeleteTodo: (Todo) -> Unit,
    onUpdateTodo: (Todo) -> Unit,
    onToggleChange: (Int, Int) -> Unit
) {
    LazyColumn(modifier = modifier.padding(10.dp)) {
        items(items = todos, key = { item -> item.id }) {
            TodoItem(
                todo = it,
                showSnackbar = showSnackbar,
                onUpdate = {updatedTodo -> onUpdateTodo(updatedTodo)},
                onDelete = { deleteTodo -> onDeleteTodo(deleteTodo) }) { toggleValue, id ->
                onToggleChange(toggleValue, id)
            }
        }
    }
}