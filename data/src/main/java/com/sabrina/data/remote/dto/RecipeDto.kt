package com.sabrina.data.remote.dto

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int? = 0,
    val missedIngredientCount: Int? = 0,
    val cuisines: List<String>? = emptyList(),
    val readyInMinutes: Int? = 0,
    val servings: Int? = 0,
    val weightWatcherSmartPoints: Int? = 0
)
