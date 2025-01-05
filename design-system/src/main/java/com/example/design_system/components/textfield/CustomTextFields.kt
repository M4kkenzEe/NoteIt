package com.example.design_system.components.textfield

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.Gray80

@Composable
fun TaskTitleTF(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (s: String) -> Unit = {},
    textHint: String = "Название",
    textColor: Color = Color.Black,
    cursorColor: Color = Color.Black,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = cursorColor,
            focusedTextColor = Color.Black,
            disabledBorderColor = Color.Black
        ),
        placeholder = {
            Text(
                text = textHint,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Gray80,
                lineHeight = 16.94.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.94.sp,
            letterSpacing = 1.2.sp,
            textAlign = TextAlign.Center
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}


@Composable
fun TaskDescriptionTF(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (s: String) -> Unit = {},
    textHint: String = "Описание",
    textColor: Color = Color.Black,
    cursorColor: Color = Color.Black,
) {
    var offset by remember {
        mutableFloatStateOf(0f)
    }

    val tfScrollState = rememberScrollableState { delta ->
        offset += delta
        delta
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .scrollable(
                orientation = Orientation.Vertical,
                state = tfScrollState
            ),
        maxLines = 40,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = cursorColor,
            focusedTextColor = Color.Black,
        ),

        placeholder = {
            Text(
                text = textHint,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Gray80,
                lineHeight = 16.94.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = textColor,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.94.sp,
            letterSpacing = 1.2.sp
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
@Preview
fun TaskTitlePrev() {

    var tfState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TaskTitleTF(
            value = tfState,
            onValueChange = { tfState = it }
        )

        TaskDescriptionTF(
            value = tfState,
            onValueChange = { tfState = it }
        )
    }


}