package com.example.addtask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addtask.data.model.AddedTaskDomainModel
import com.example.addtask.domain.usecase.AddTaskUseCase
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddTaskViewModel(private val useCase: AddTaskUseCase) : ViewModel() {

    fun addTask(title: String, desc: String, priorityTag: PriorityTag) {
        viewModelScope.launch(Dispatchers.IO) {
            val taskDomain = AddedTaskDomainModel(
                id = 0,
                title = title,
                description = desc,
                tableTag = TableTag.NOT_STARTED,
                priorityTag = priorityTag,
                createdAt = LocalDate.now()
            )
            useCase.execute(taskDomain)
        }
    }
}