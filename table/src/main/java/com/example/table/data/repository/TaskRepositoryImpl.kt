package com.example.table.data.repository

import com.example.database.internal.dao.TaskDao
import com.example.database.internal.entities.TaskEntity
import com.example.table.domain.model.TaskDomain
import com.example.table.data.mapper.toEntity
import com.example.table.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllData()
    }

    override suspend fun insertTask(taskModel: TaskDomain) {
        taskDao.insertTask(taskModel.toEntity())
    }

    override suspend fun editTask(taskModel: TaskDomain) {
        taskDao.editTask(
            id = taskModel.id,
            title = taskModel.title,
            description = taskModel.description,
            tag = taskModel.tableTag.toString(),
            priorityTag = taskModel.priorityTag.toString()
        )
    }

    override suspend fun deleteTask(taskModel: TaskDomain) {
        taskDao.deleteTask(taskModel.id)
    }
}