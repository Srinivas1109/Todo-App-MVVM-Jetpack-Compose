package com.srinivas.todo.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.srinivas.todo.data.Todo

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todo: Todo,
    showSnackbar: MutableState<Boolean>,
    onUpdate: (Todo) -> Unit,
    onDelete: (Todo) -> Unit,
    onToggle: (Int, Int) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val openDialog = remember {
        mutableStateOf(false)
    }

    val editTitle = remember {
        mutableStateOf(todo.title)
    }

//    val showSnackBar = remember {
//        mutableStateOf(false)
//    }

    val editDescription = remember {
        mutableStateOf(todo.description)
    }

    // Edit dialog
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            modifier = Modifier.fillMaxWidth(),
            title = { Text(text = "Edit Todo") },
            text = {
                Column() {
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        value = editTitle.value,
                        onValueChange = { editTitle.value = it },
                        placeholder = { Text(text = "Title") },
                        label = { Text(text = "Title") }
                    )
                    Spacer(modifier = modifier.height(10.dp))

                    editDescription.value?.let {
                        OutlinedTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            value = it,
                            onValueChange = { text -> editDescription.value = text },
                            placeholder = { Text(text = "Description") },
                            label = { Text(text = "Description") }
                        )
                    }
                }
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    Button(onClick = { openDialog.value = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        onUpdate(
                            todo.copy(
                                title = editTitle.value,
                                description = editDescription.value
                            )
                        )
                        openDialog.value = false
                    }) {
                        Text(text = "Update")
                    }
                }

            }
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        openDialog.value = true
                    }
                )
            },
        elevation = 8.dp
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(8.dp).fillMaxWidth(0.75f)
            ) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.h5
                )
                if (todo.description != null) {
                    Text(
                        text = todo.description,
                        style = MaterialTheme.typography.body1,
                        overflow = if(todo.description.length > 30) {
                            TextOverflow.Ellipsis
                        }else{
                            TextOverflow.Visible
                        }
                    )
                }
            }
            Row() {
                Checkbox(
                    checked = todo.isDone,
                    onCheckedChange = {
                        val toggleValue = if (todo.isDone) {
                            0
                        } else {
                            1
                        }
                        onToggle(toggleValue, todo.id)
                    }
                )
                IconButton(onClick = {
                    onDelete(todo)
                    showSnackbar.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }


        }
    }
}