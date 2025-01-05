package com.example.addtask.domain.repository

import com.example.addtask.data.model.AddedTaskDomainModel

interface AddTaskRepository {
    suspend fun insertTask(taskModel: AddedTaskDomainModel)
}