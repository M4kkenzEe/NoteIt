package com.example.addtask.domain.usecase

import com.example.addtask.data.model.AddedTaskDomainModel
import com.example.addtask.domain.repository.AddTaskRepository

class AddTaskUseCase(private val taskRepository: AddTaskRepository) {
    suspend fun execute(task: AddedTaskDomainModel){
        taskRepository.insertTask(task)
    }
}
