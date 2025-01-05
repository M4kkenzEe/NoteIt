package com.example.design_system.components.toast

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun NoteToast(message: String, onDismiss: () -> Unit) {

    var offsetY by remember { mutableFloatStateOf(-100f) }

    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    LaunchedEffect(Unit) {
        offsetY = 18f
        delay(2000)
        offsetY = -100f
        delay(300)
        onDismiss()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .offset(y = animatedOffsetY.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .height(42.dp)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .background(Color.White)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            )
        }
    }
}

@Preview
@Composable
fun NoteToastPrev() {
    var showToast by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { showToast = true },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Показать Toast")
        }

        if (showToast) {
            NoteToast(message = "Это кастомный Toast!") {
                showToast = false
            }
        }
    }
}