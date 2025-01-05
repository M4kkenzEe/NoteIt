package com.example.table.data.di

import com.example.table.data.repository.TaskRepositoryImpl
import com.example.table.domain.repository.TaskRepository
import com.example.table.domain.usecase.TaskUseCase
import com.example.table.presentation.TableViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val TableModule = module {
    single<TaskRepository> { TaskRepositoryImpl(taskDao = get()) }
    factory { TaskUseCase(taskRepository = get()) }
    viewModel { TableViewModel(taskUseCase = get()) }
}