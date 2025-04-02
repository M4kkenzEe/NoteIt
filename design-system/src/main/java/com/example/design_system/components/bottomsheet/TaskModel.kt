package com.example.design_system.components.bottomsheet

import androidx.compose.runtime.Immutable
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag
import java.time.LocalDate
import java.time.LocalDateTime

@Immutable
data class TaskModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val tableTag: TableTag = TableTag.NOT_STARTED,
    val priorityTag: PriorityTag = PriorityTag.GREEN,
    val createdAt: LocalDate,
    val lastUpdated: LocalDateTime,
)