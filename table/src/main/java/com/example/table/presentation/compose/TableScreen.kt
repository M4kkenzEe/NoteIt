package com.example.table.presentation.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.GrayBorder
import com.example.design_system.GrayEe
import com.example.design_system.components.bottomsheet.BottomSheetScreen
import com.example.design_system.components.card.CardTaskScreen
import com.example.design_system.model.TableTag
import com.example.table.R
import com.example.table.domain.model.TaskDomain
import com.example.table.presentation.TableViewModel
import com.example.table.presentation.toModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TableScreen(viewModel: TableViewModel = koinViewModel()) {
    val totalPages = 1000
    val pagerState = rememberPagerState(
        pageCount = { totalPages },
        initialPage = totalPages / 2
    )

    val tasksByCategory by viewModel.viewState.collectAsState()

    val notStartedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.NOT_STARTED)
            ?.filter { it.createdAt == viewModel.todayIsState.value }?.sortedBy { it.lastUpdated }
            ?.reversed()
            ?: emptyList()
    val inProgressTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.IN_PROGRESS)
            ?.filter { it.createdAt == viewModel.todayIsState.value }?.sortedBy { it.lastUpdated }
            ?.reversed()
            ?: emptyList()
    val finishedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.FINISHED)
            ?.filter { it.createdAt == viewModel.todayIsState.value }?.sortedBy { it.lastUpdated }
            ?.reversed()
            ?: emptyList()

    var sheetState by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    val backToCurrentDateAction = remember {
        {
            viewModel.todayIsState.value = LocalDate.now()
            coroutineScope.launch { pagerState.animateScrollToPage(totalPages / 2) }
        }
    }

    Surface(modifier = Modifier.background(Color.White)) {
        if (sheetState) {
            BottomSheetScreen(
                cancelAdding = { sheetState = false },
                editTask = { title, desc, priorityTag ->
                    viewModel.editTask(
                        title = title,
                        desc = desc,
                        priorityTag = priorityTag
                    )
                },
                task = viewModel.currentTaskState.value?.toModel()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CalendarRow(
                pagerState = pagerState,
                totalPages = totalPages,
                backToCurrentDateClick = { backToCurrentDateAction() }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_not_started),
                    taskList = notStartedTasks,
                    swipeToLeft = { viewModel.deleteTask(it) },
                    swipeToRight = { viewModel.setTag(it, TableTag.IN_PROGRESS) },
                    onTaskClick = { task ->
                        sheetState = true
                        viewModel.currentTaskState.value = task
                        Log.d(
                            "MyTag",
                            "Current task is:\n" + viewModel.currentTaskState.value.toString()
                        )
                    }
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_in_progress),
                    taskList = inProgressTasks,
                    swipeToLeft = { viewModel.setTag(it, TableTag.NOT_STARTED) },
                    swipeToRight = { viewModel.setTag(it, TableTag.FINISHED) },
                    onTaskClick = { task ->
                        sheetState = true
                        viewModel.currentTaskState.value = task
                    }
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_finished),
                    taskList = finishedTasks,
                    swipeToLeft = { viewModel.setTag(it, TableTag.IN_PROGRESS) },
                    swipeToRight = { viewModel.deleteTask(it) },
                    onTaskClick = { task ->
                        sheetState = true
                        viewModel.currentTaskState.value = task
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskColumn(
    modifier: Modifier = Modifier,
    label: String = "Не начатые",
    taskList: List<TaskDomain>,
    onTaskClick: (task: TaskDomain) -> Unit = {},
    swipeToLeft: (task: TaskDomain) -> Unit = {},
    swipeToRight: (task: TaskDomain) -> Unit = {},
) {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    //TODO перенести в отдельный файл компонент
    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = { isContextMenuVisible = false },
        modifier = Modifier.height(50.dp),
        offset = DpOffset(100.dp, 100.dp)
    ) {
        DropdownMenuItem(
            text = { Text(text = "Copy", fontSize = 6.sp, fontWeight = FontWeight(400)) },
            onClick = { isContextMenuVisible = false },
            modifier = Modifier.height(20.dp)
        )
        DropdownMenuItem(
            text = { Text(text = "Delete", fontSize = 6.sp, fontWeight = FontWeight(400)) },
            onClick = { isContextMenuVisible = false },
            modifier = Modifier.height(20.dp)
        )
    }

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 14.52.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .border(
                    BorderStroke(0.5.dp, GrayBorder),
                    RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
                .background(GrayEe)
                .height(28.dp)
                .padding(top = 8.dp),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(0.5.dp, GrayBorder))
                .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(taskList, key = { task -> task.id }) { task ->

                val swipeToDismissState = rememberSwipeToDismissBoxState()

                when (swipeToDismissState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        swipeToRight(task)
                    }

                    SwipeToDismissBoxValue.EndToStart -> {
                        swipeToLeft(task)
                    }

                    else -> {}
                }

                SwipeToDismissBox(
                    state = swipeToDismissState,
                    backgroundContent = {},
                ) {
                    CardTaskScreen(
                        taskTitle = task.title,
                        taskDesc = task.description,
                        taskStatus = task.tableTag,
                        onClick = { onTaskClick(task) },
                        onLongClick = { isContextMenuVisible = true },
                        taskPriority = task.priorityTag
                    )
                }
            }
        }

    }
}


@Composable
@Preview
fun TableScreenPrev() {
    TableScreen()
}