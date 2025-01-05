package com.example.noteitapp.navigation

import com.example.noteitapp.R


sealed class BottomBarScreens(val route: String, val title: String, val icon: Int) {
    data object AddTaskScreen : BottomBarScreens(
        route = "route_add",
        title = "Добавить задачу",
        icon = R.drawable.ic_add_task
    )

    data object FocusScreen : BottomBarScreens(
        route = "route_focus",
        title = "Сфокусироваться",
        icon = R.drawable.ic_focus
    )

    data object TasksScreen : BottomBarScreens(
        route = "route_tasks",
        title = "Задачи",
        icon = R.drawable.ic_tasks
    )

    data object ProgressScreen : BottomBarScreens(
        route = "route_progress",
        title = "Прогресс",
        icon = R.drawable.ic_progress
    )

}
