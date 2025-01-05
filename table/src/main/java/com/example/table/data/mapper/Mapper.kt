package com.example.table.data.mapper

import com.example.database.internal.entities.TaskEntity
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag
import com.example.table.domain.model.TaskDomain
import java.time.LocalDate

fun TaskDomain.toEntity() =
    TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        tableTag = this.tableTag.toString(),
        priorityTag = this.priorityTag.toString(),
        createdAt = this.createdAt.toString()
    )

fun TaskEntity.toDomain() =
    TaskDomain(
        id = this.id,
        title = this.title ?: "Unknown title",
        description = this.description ?: "U r lazy but",
        tableTag = TableTag.valueOf(this.tableTag ?: TableTag.NOT_STARTED.toString()),
        priorityTag = PriorityTag.valueOf(
            this.priorityTag ?: PriorityTag.GREEN.toString()
        ),
        createdAt = LocalDate.parse(this.createdAt)
    )
