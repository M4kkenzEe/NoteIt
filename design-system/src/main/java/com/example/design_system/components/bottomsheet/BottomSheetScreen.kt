package com.example.design_system.components.bottomsheet

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.components.buttons.DoneButton
import com.example.design_system.components.buttons.ManageBox
import com.example.design_system.components.textfield.TaskDescriptionTF
import com.example.design_system.components.textfield.TaskTitleTF
import com.example.design_system.mappers.toInt
import com.example.design_system.model.PriorityTag
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    cancelAdding: () -> Unit = {},
    editTask: (title: String, desc: String, priorityTag: PriorityTag) -> Unit = { _, _, _ -> },
    task: TaskModel? = null
) {
    Log.d(
        "MyTag",
        "Current task is(from bottomsheet):\n" + task?.toString()
    )
    val context = LocalContext.current

    var titleState by remember {
        mutableStateOf(task?.title ?: "")
    }

    var descState by remember {
        mutableStateOf(task?.description ?: "")
    }

    var priorityTag by remember {
        mutableStateOf(task?.priorityTag ?: PriorityTag.GREEN)
    }

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            cancelAdding()
        },
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TaskTitleTF(
                value = titleState,
                onValueChange = { titleState = it },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ManageBox(
                    icon = R.drawable.ic_calendar_white,
                    text = LocalDate.now().toString()
                )
                ManageBox(icon = R.drawable.ic_sandtimer_white)
                ManageBox(icon = R.drawable.ic_clock_white)
                ManageBox(
                    icon = priorityTag.toInt(),
                    onClick = {
                        priorityTag = when (priorityTag) {
                            PriorityTag.RED -> {
                                PriorityTag.GREEN
                            }

                            PriorityTag.ORANGE -> {
                                PriorityTag.RED
                            }

                            PriorityTag.GREEN -> {
                                PriorityTag.ORANGE
                            }
                        }
                    },
                    text = "Приоритет"
                )
            }

            TaskDescriptionTF(
                value = descState,
                onValueChange = { descState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            DoneButton(onClick = {
                editTask(titleState, descState, priorityTag)
                cancelAdding()
            })
        }
    }
}