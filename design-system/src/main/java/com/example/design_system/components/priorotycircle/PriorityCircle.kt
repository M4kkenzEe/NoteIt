package com.example.design_system.components.priorotycircle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.design_system.PriorityGreen
import com.example.design_system.PriorityOrange
import com.example.design_system.PriorityRed
import com.example.design_system.model.PriorityTag

@Composable
fun PriorityCircle(modifier: Modifier = Modifier, taskPriority: PriorityTag) {

    val priorityColor = when (taskPriority) {
        PriorityTag.RED -> PriorityRed
        PriorityTag.ORANGE -> PriorityOrange
        PriorityTag.GREEN -> PriorityGreen
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(8.dp)
            .background(priorityColor)
    )
}