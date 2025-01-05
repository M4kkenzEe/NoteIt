package com.example.noteitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.design_system.GrayE3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Color.White.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = GrayE3.toArgb(),
                darkScrim = GrayE3.toArgb()
            )
        )
        setContent {
            MainScreen()
        }
    }
}