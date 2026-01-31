package com.sabrina.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Boolean = false
)
