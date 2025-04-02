package com.example.design_system.components.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteToast() {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var isShow1 by remember { mutableStateOf(true) }
        if (isShow1)
            InactiveCartProduct {
                isShow1 = false
            }
        Spacer(modifier = Modifier.height(20.dp))
        var isShow2 by remember { mutableStateOf(true) }
        if (isShow2)
            InactiveCartProduct {
                isShow2 = false
            }
        Spacer(modifier = Modifier.height(20.dp))
        var isShow3 by remember { mutableStateOf(true) }
        if (isShow3)
            InactiveCartProduct {
                isShow3 = false
            }
        Spacer(modifier = Modifier.height(20.dp))
        var isShow4 by remember { mutableStateOf(true) }
        if (isShow4)
            InactiveCartProduct {
                isShow4 = false
            }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
internal fun InactiveCartProduct(onDelete: () -> Unit) {
    SwipeToDelete(
        content = {
            Box(
                modifier = Modifier.background(Color.Black),
            ) {
                Text("FFFFFffffF")
            }
        },
        onDelete = {
            onDelete()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SwipeToDelete(
    content: @Composable () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = { totalWidth -> totalWidth * 0.5f },
        confirmValueChange = { newValue ->
            if (newValue == SwipeToDismissBoxValue.EndToStart) {
                true
            } else {
                false
            }
        },
    )

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onDelete()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            DismissBackground()
        },
        content = {
            content()
        },
    )
}


@Composable
private fun DismissBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = "<KZZZZZZZZ",
            color = Color.White,
            fontSize = 24.sp
        )
    }
}
