package com.example.database.internal.di

import com.example.database.internal.TaskDatabase
import org.koin.dsl.module

val DBModule = module {
    single { TaskDatabase.getDatabase(get()) }
    single { get<TaskDatabase>().taskDao() }
}
