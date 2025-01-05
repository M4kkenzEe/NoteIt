package com.example.desk.domain.repository.api

import com.example.database.internal.entities.TaskEntity
import com.example.desk.domain.model.TaskDomain
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun insertTask(taskModel: TaskDomain)
    suspend fun editTask(taskModel: TaskDomain)
    suspend fun deleteTask(taskModel: TaskDomain)
}