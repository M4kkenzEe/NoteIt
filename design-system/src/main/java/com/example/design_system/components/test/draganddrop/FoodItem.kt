package com.example.design_system.components.test.draganddrop

import androidx.annotation.DrawableRes
import com.example.design_system.R

data class FoodItem(val id: Int, val name: String, val price: Double, @DrawableRes val image: Int)

val foodList = listOf(
    FoodItem(1, "Pizza", 20.0, R.drawable.food_pizza),
    FoodItem(2, "French toast", 10.05, R.drawable.food_toast),
    FoodItem(3, "Chocolate cake", 12.99, R.drawable.food_cake),
)

data class Person(val id: Int, val name: String, @DrawableRes val profile: Int)

val persons = listOf(
    Person(1, "Jhone", R.drawable.user_one),
    Person(2, "Eyle", R.drawable.user_two),
    Person(3, "Tommy", R.drawable.user_three),
)