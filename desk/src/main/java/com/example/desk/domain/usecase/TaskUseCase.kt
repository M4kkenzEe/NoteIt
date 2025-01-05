package com.example.desk.domain.usecase

import com.example.database.internal.entities.TaskEntity
import com.example.desk.domain.model.TaskDomain
import com.example.desk.domain.repository.api.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskUseCase(private val repository: TaskRepository) {

    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return repository.getAllTasks()
    }

    suspend fun addTask(task: TaskDomain) {
        repository.insertTask(task)
    }
}