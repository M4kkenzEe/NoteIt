package com.example.table.domain.model

import androidx.compose.runtime.Immutable
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag
import java.time.LocalDate

@Immutable
data class TaskDomain(
    val id: Int = 0,
    val title: String,
    val description: String,
    val tableTag: TableTag = TableTag.NOT_STARTED,
    val priorityTag: PriorityTag = PriorityTag.GREEN,
    val createdAt: LocalDate
)
