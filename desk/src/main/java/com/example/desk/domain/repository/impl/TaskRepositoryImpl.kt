package com.example.desk.domain.repository.impl

import com.example.database.internal.dao.TaskDao
import com.example.database.internal.entities.TaskEntity
import com.example.desk.domain.mapper.toEntity
import com.example.desk.domain.model.TaskDomain
import com.example.desk.domain.repository.api.TaskRepository
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