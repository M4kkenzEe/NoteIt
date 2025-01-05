package com.example.addtask.data.repository

import com.example.addtask.data.mapper.toEntity
import com.example.addtask.data.model.AddedTaskDomainModel
import com.example.addtask.domain.repository.AddTaskRepository
import com.example.database.internal.dao.TaskDao

class AddTaskRepositoryImpl(private val taskDao: TaskDao) : AddTaskRepository {
    override suspend fun insertTask(taskModel: AddedTaskDomainModel) {
        taskDao.insertTask(taskModel.toEntity())
        taskDao.getAllData()
    }
}