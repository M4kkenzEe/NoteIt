package com.example.table.presentation

import com.example.design_system.components.bottomsheet.TaskModel
import com.example.table.domain.model.TaskDomain

fun TaskModel.toDomain() = TaskDomain(
    id = id,
    title = title,
    description = description,
    tableTag = tableTag,
    priorityTag = priorityTag,
    createdAt = createdAt,
    lastUpdated = lastUpdated
)

fun TaskDomain.toModel() = TaskModel(
    id = id,
    title = title,
    description = description,
    tableTag = tableTag,
    priorityTag = priorityTag,
    createdAt = createdAt,
    lastUpdated = lastUpdated
)
