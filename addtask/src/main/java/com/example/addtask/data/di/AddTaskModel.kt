package com.example.addtask.data.di

import com.example.addtask.data.repository.AddTaskRepositoryImpl
import com.example.addtask.domain.repository.AddTaskRepository
import com.example.addtask.domain.usecase.AddTaskUseCase
import com.example.addtask.presentation.AddTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AddTaskModule = module {
    viewModel { AddTaskViewModel(useCase = get()) }
    single<AddTaskRepository> { AddTaskRepositoryImpl(taskDao = get()) }
    factory { AddTaskUseCase(taskRepository = get()) }
}