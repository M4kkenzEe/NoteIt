package com.example.design_system.mappers

import com.example.design_system.model.PriorityTag

fun PriorityTag.toInt(): Int {
    return when (this) {
        PriorityTag.ORANGE -> 1
        PriorityTag.RED -> 2
        else -> 0
    }
}