package com.example.desk.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DeskScreen(
    viewModel: DeskViewModel = koinViewModel()
) {
    val tasksByCategory by viewModel.viewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..10){
            item {
                Text("item is: $i")
            }
        }
        if (tasksByCategory != null) {
            items(tasksByCategory ?: emptyList()) { task ->
                Text(
                    "${task.title}\n${task.id}\n${task.description}",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            item {
                Button(
                    onClick = {
                        viewModel.addTask()
                    },
                ) {
                    Text("Add")
                }
            }
        } else {
            item {
                Button(
                    onClick = {
                        viewModel.addTask()
                    },
                ) {
                    Text("Add please smth")
                }
            }
        }
    }

}