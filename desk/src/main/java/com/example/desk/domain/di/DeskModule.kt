package com.example.desk.domain.di

import com.example.desk.domain.repository.api.TaskRepository
import com.example.desk.domain.repository.impl.TaskRepositoryImpl
import com.example.desk.domain.usecase.TaskUseCase
import com.example.desk.presentation.DeskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val DeskModule1 = module {
    single<TaskRepository> { TaskRepositoryImpl(taskDao = get()) }

    factory { TaskUseCase(repository = get()) }


    viewModel { DeskViewModel(useCase = get()) }
}

