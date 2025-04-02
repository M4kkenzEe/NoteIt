package com.example.noteitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.addtask.presentation.AddTaskScreen
import com.example.design_system.components.toast.NoteToast
import com.example.table.presentation.compose.TableScreen

@Composable
fun BottomNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.TasksScreen.route,
        modifier = modifier
    ) {
        composable(BottomBarScreens.AddTaskScreen.route) {
            AddTaskScreen(backToTable = { navController.navigate(BottomBarScreens.TasksScreen.route) })
        }

        composable(BottomBarScreens.TasksScreen.route) {
            TableScreen()
        }

        composable(BottomBarScreens.FocusScreen.route) {
//            TimerView()
        }

        composable(BottomBarScreens.ProgressScreen.route) {
            NoteToast()
        }
    }
}