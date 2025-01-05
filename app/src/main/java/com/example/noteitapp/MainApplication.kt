package com.example.noteitapp

import android.app.Application
import com.example.addtask.data.di.AddTaskModule
import com.example.database.internal.di.DBModule
import com.example.table.data.di.TableModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@MainApplication)
            modules(
                DBModule,
                TableModule,
                AddTaskModule
            )
        }
    }
}