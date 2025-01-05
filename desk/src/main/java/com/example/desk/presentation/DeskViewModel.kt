package com.example.desk.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desk.domain.usecase.TaskUseCase
import com.example.desk.domain.mapper.toDomain
import com.example.desk.domain.model.PriorityTag
import com.example.desk.domain.model.TableTag
import com.example.desk.domain.model.TaskDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.LocalDate

class DeskViewModel(private val useCase: TaskUseCase) : ViewModel() {

    init {
        getAllTasks()
    }

    private val _viewState: MutableStateFlow<List<TaskDomain>?> = MutableStateFlow(null)
    val viewState: StateFlow<List<TaskDomain>?> get() = _viewState

    private fun getAllTasks() {
        viewModelScope.launch {
            useCase.getAllTasks()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.d(
                        "YURASLOG",
                        "Error (getAllTasks):\n${it.message}\n${it.localizedMessage}"
                    )
                }
                .collect { it ->
                    Log.d("YURASLOG", "Success! (getAllTasks)\n$it")
                    _viewState.value = it.map { it.toDomain() }
                }
        }
    }

    fun addTask() {
        val task = TaskDomain(
            id = 0,
            title = "TestTitle",
            description = "TestDesc",
            tableTag = TableTag.NOT_STARTED,
            priorityTag = PriorityTag.GREEN,
            createdAt = LocalDate.now()
        )
        viewModelScope.launch {
            try {
                useCase.addTask(task)
            } catch (e: Exception) {
                Log.d("YURASLOG", "${e.localizedMessage}")
            }
        }
        getAllTasks()
    }
}