package com.sabrina.domain.model

data class RecipeDetail(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val summary: String,
    val instructions: String,
    val ingredients: List<String>,
    val readyInMinutes: Int,
    val servings: Int,
    val healthScore: Int,
    val isFavorite: Boolean = false
)
