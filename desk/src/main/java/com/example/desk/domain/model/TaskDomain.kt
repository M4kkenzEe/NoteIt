package com.example.desk.domain.model

import androidx.compose.runtime.Immutable
import com.example.desk.domain.model.PriorityTag
import com.example.desk.domain.model.TableTag
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
