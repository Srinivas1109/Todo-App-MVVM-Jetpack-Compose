package com.srinivas.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.srinivas.todo.data.TodoDatabase
import com.srinivas.todo.data.TodoLocalRepository
import com.srinivas.todo.ui.screens.HomeScreen
import com.srinivas.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoDao = TodoDatabase.getInstance(application).todoDao
        val todoLocalRepository = TodoLocalRepository(todoDao)
        val factory = TodoViewModelFactory(todoLocalRepository)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodoApp(factory = factory)
                }
            }
        }
    }
}
