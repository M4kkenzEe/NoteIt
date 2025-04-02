package com.example.database.internal.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String?,
    @ColumnInfo val description: String?,
    @ColumnInfo val tableTag: String?,
    @ColumnInfo val priorityTag: String?,
    @ColumnInfo val createdAt: String,
    @ColumnInfo val lastUpdated: String
)