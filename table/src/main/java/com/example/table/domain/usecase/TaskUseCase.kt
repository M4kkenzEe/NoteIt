package com.example.table.domain.usecase

import com.example.database.internal.entities.TaskEntity
import com.example.table.domain.model.TaskDomain
import com.example.table.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun insertTask(taskModel: TaskDomain) {
        taskRepository.insertTask(taskModel = taskModel)
    }

    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getAllTasks()
    }

    suspend fun deleteTask(taskDomain: TaskDomain) {
        taskRepository.deleteTask(taskModel = taskDomain)
    }

    suspend fun editTask(taskDomain: TaskDomain) {
        taskRepository.editTask(taskModel = taskDomain)
    }
}