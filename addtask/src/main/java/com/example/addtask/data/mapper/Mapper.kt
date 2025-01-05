package com.example.addtask.data.mapper

import com.example.addtask.data.model.AddedTaskDomainModel
import com.example.database.internal.entities.TaskEntity

fun AddedTaskDomainModel.toEntity() =
    TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        tableTag = this.tableTag.toString(),
        priorityTag = this.priorityTag.toString(),
        createdAt = this.createdAt.toString()
    )