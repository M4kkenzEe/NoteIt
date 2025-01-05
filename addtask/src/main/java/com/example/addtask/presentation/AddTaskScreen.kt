package com.example.addtask.presentation

import android.view.ViewTreeObserver
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.design_system.components.buttons.ManageBox
import com.example.design_system.components.textfield.TaskDescriptionTF
import com.example.design_system.components.textfield.TaskTitleTF
import com.example.design_system.mappers.toInt
import com.example.design_system.model.PriorityTag
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = koinViewModel(),
    backToTable: () -> Unit = {}
) {
    var titleTfState by remember {
        mutableStateOf("")
    }

    var descTfState by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    val isKeyBoardOpened by keyboardAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .padding(bottom = if (isKeyBoardOpened) 0.dp else 52.dp),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 28.dp),
        ) {
            TaskTitleTF(
                value = titleTfState,
                onValueChange = { titleTfState = it },
                textHint = "Untitled",
                textColor = Color.Black,
                cursorColor = Color.Black,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            HorizontalDivider(color = Color.Gray, modifier = Modifier.fillMaxWidth())

            TaskDescriptionTF(
                value = descTfState,
                onValueChange = { descTfState = it },
                textHint = "Start writing...",
                textColor = Color.Black,
                cursorColor = Color.Black,
                modifier = Modifier.weight(1f)
            )
        }

        ButtonsTestRow(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart),
            saveTask = { priorityTag ->
                viewModel.addTask(
                    title = titleTfState,
                    desc = descTfState,
                    priorityTag = priorityTag
                )
                backToTable()
            }
        )
    }
}

@Composable
fun ButtonsTestRow(modifier: Modifier = Modifier, saveTask: (tag: PriorityTag) -> Unit = { _ -> }) {

    var priorityTag by remember {
        mutableStateOf(PriorityTag.GREEN)
    }

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = { saveTask(priorityTag) }) {
            Text(text = "Сохранить")
        }

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
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val view = LocalView.current
    var isImeVisible by remember { mutableStateOf(false) }

    DisposableEffect(LocalWindowInfo.current) {
        val listener = ViewTreeObserver.OnPreDrawListener {
            isImeVisible = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) == true
            true
        }
        view.viewTreeObserver.addOnPreDrawListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnPreDrawListener(listener)
        }
    }
    return rememberUpdatedState(isImeVisible)
}

@Composable
@Preview
fun AddTaskScreenPrev() {
    AddTaskScreen()
}