package com.srinivas.todo.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputBlock(
    modifier: Modifier = Modifier,
    title: MutableState<String>,
    description: MutableState<String>,
    isDone: MutableState<Boolean>,
    context: Context = LocalContext.current,
    save: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = { Text(text = "Title") },
            label = { Text(text = "Title") }
        )
        Spacer(modifier = modifier.height(10.dp))
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = { Text(text = "Description") },
            label = { Text(text = "Description") }
        )
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Mark as done")
            Checkbox(checked = isDone.value, onCheckedChange = { isDone.value = it })
        }

        Button(
            onClick = {
                if (title.value.isNotBlank()) {
                    save()
                } else {
                    Toast.makeText(context, "Title should not be empty!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Save",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}