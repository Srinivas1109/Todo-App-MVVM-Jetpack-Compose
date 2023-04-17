package com.srinivas.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srinivas.todo.data.Todo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    showSnackbar: MutableState<Boolean>,
    todos: List<Todo>,
    titleInput: MutableState<String>,
    descriptionInput: MutableState<String>,
    isDoneCheckbox: MutableState<Boolean>,
    addTodoItem: (Todo) -> Unit,
    updateTodoItem: (Todo) -> Unit,
    deleteTodoItem: (Todo) -> Unit,
    toggleTodoItem: (Int, Int) -> Unit
){
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "New Todo",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        InputBlock(title = titleInput, description = descriptionInput, isDone = isDoneCheckbox) {
            addTodoItem(
                Todo(
                    title = titleInput.value,
                    description = descriptionInput.value,
                    isDone = isDoneCheckbox.value
                )
            )
            titleInput.value = ""
            descriptionInput.value = ""
            isDoneCheckbox.value = false
        }
        Divider(
            modifier = modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        TodoList(
            todos = todos,
            showSnackbar = showSnackbar,
            onUpdateTodo = { updatedTodo ->
                updateTodoItem(
                    updatedTodo
                )
            },
            onDeleteTodo = { deleteTodo -> deleteTodoItem(deleteTodo) }) { toggleValue, id ->
            toggleTodoItem(toggleValue, id)
        }
    }
}




