package com.example.table.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag
import com.example.table.domain.model.TaskDomain
import com.example.table.domain.usecase.TaskUseCase
import com.example.table.data.mapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.LocalDate

class TableViewModel(private val taskUseCase: TaskUseCase) : ViewModel() {

    val currentTaskState = mutableStateOf<TaskDomain?>(null)

    private val _viewState: MutableStateFlow<List<TaskDomain>?> = MutableStateFlow(null)
    val viewState: StateFlow<List<TaskDomain>?> get() = _viewState

    val todayIsState = mutableStateOf(LocalDate.now())

    private fun getAllTasks() {
        viewModelScope.launch {
            taskUseCase.getAllTasks()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.d(
                        "DB",
                        "Error (getAllTasks):\n${it.message}\n${it.localizedMessage}"
                    )
                }
                .collect { it ->
                    Log.d("DB", "Success! (getAllTasks)\n$it")
                    _viewState.value = it.map { it.toDomain() }
                }
        }
    }

    fun editTask(title: String, desc: String, priorityTag: PriorityTag) {
        val task = TaskDomain(
            id = currentTaskState.value?.id!!,
            title = title,
            description = desc,
            tableTag = currentTaskState.value?.tableTag!!,
            priorityTag = priorityTag,
            createdAt = currentTaskState.value?.createdAt!!,
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskUseCase.editTask(task)
            } catch (e: Exception) {
                Log.d("DB", "Error! (editTask)\n${e.message}\n${e.localizedMessage}")
            }
        }
        getAllTasks()
    }

    fun setTag(taskDomain: TaskDomain, tag: TableTag) {
        val resultTask = TaskDomain(
            id = taskDomain.id,
            title = taskDomain.title,
            description = taskDomain.description,
            tableTag = tag,
            createdAt = taskDomain.createdAt,
            priorityTag = taskDomain.priorityTag
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskUseCase.editTask(resultTask)
            } catch (e: Exception) {
                Log.d("DB", "Error! (editTask)\n${e.message}\n${e.localizedMessage}")
            }
        }
        getAllTasks()
    }

    fun deleteTask(task: TaskDomain) {
        viewModelScope.launch {
            try {
                taskUseCase.deleteTask(task)
            } catch (e: Exception) {
                Log.d("DB", "Error! (deleteTask)\n${e.message}\n${e.localizedMessage}")
            }
            currentTaskState.value = null
            getAllTasks()
        }
    }

    init {
        getAllTasks()
    }

}