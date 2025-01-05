package com.example.design_system.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.Gray80
import com.example.design_system.GrayBorder
import com.example.design_system.GrayF3
import com.example.design_system.components.priorotycircle.PriorityCircle
import com.example.design_system.model.PriorityTag
import com.example.design_system.model.TableTag


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardTaskScreen(
    modifier: Modifier = Modifier,
    taskTitle: String = "task title",
    taskDesc: String = "task desc",
    taskStatus: TableTag = TableTag.NOT_STARTED,
    taskPriority: PriorityTag = PriorityTag.GREEN,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    val cardColor = if (taskStatus == TableTag.FINISHED) GrayF3 else Color.White
    val textStyle =
        if (taskStatus == TableTag.FINISHED) TextStyle(textDecoration = TextDecoration.LineThrough)
        else TextStyle(textDecoration = TextDecoration.None)

    Box(
        modifier = modifier
            .border(BorderStroke(0.5.dp, GrayBorder), RoundedCornerShape(20))
            .clip(RoundedCornerShape(20))
            .background(cardColor)
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = { onClick() },
                onLongClick = { onLongClick() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
                .padding(start = 8.dp)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = taskTitle,
                fontWeight = FontWeight(600),
                fontSize = 12.sp,
                lineHeight = 12.1.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                maxLines = 3,
                style = textStyle
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "12:00",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray80,
                    style = textStyle
                )

                PriorityCircle(taskPriority = taskPriority, modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
@Preview
fun CardTaskScreenPrev() {
    Column(
        modifier = Modifier
            .padding(100.dp)
            .fillMaxHeight()
            .width(104.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        CardTaskScreen(taskStatus = TableTag.FINISHED)
        CardTaskScreen()
        CardTaskScreen(taskStatus = TableTag.FINISHED)
        CardTaskScreen()
    }

}

