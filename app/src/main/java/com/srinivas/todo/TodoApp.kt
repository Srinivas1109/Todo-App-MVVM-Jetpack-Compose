package com.srinivas.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srinivas.todo.ui.screens.HomeScreen
import com.srinivas.todo.ui.screens.TodoViewModel

@Composable
fun TodoApp(
    factory: TodoViewModelFactory,
    modifier: Modifier = Modifier
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val showSnackbar = remember {
        mutableStateOf(false)
    }

    val todoViewModel: TodoViewModel = viewModel(factory = factory)

    val todos = todoViewModel.todos.collectAsState(initial = emptyList())

    val titleInput = remember {
        mutableStateOf("")
    }
    val descriptionInput = remember {
        mutableStateOf("")
    }
    val isDoneCheckbox = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Todo App",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 10.dp)
                )
            })
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeScreen(
                showSnackbar = showSnackbar,
                todos = todos.value,
                titleInput = titleInput,
                descriptionInput = descriptionInput,
                isDoneCheckbox = isDoneCheckbox,
                addTodoItem = { newTodo -> todoViewModel.addTodo(newTodo) },
                updateTodoItem = { updatedTodo -> todoViewModel.updateTodo(updatedTodo) },
                deleteTodoItem = { deleteTodo ->
                    todoViewModel.deleteTodo(deleteTodo)
                    todoViewModel.cachedTodo = deleteTodo
                },
                toggleTodoItem = { toggleValue, id -> todoViewModel.toggleTodo(toggleValue, id) }
            )
            if (showSnackbar.value) {
                LaunchedEffect(key1 = showSnackbar.value) {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Deleted Successfully",
                        actionLabel = "undo",
                        duration = SnackbarDuration.Short
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            // re-insert the cached todoItem
                            todoViewModel.cachedTodo?.let { cachedTodo -> todoViewModel.addTodo(cachedTodo) }
                        }
                        SnackbarResult.Dismissed -> {
                            showSnackbar.value = false
                            todoViewModel.cachedTodo = null
                            // make cached to todoItem null
                        }
                    }

                    showSnackbar.value = false
//                }
                }
            }
        }
    }

}

