package com.sabrina.data.remote.dto

data class RecipeDetailDto(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val instructions: String?,
    val extendedIngredients: List<ExtendedIngredientDto>,
    val readyInMinutes: Int,
    val servings: Int,
    val healthScore: Int,
    val dishTypes: List<String>
)

data class ExtendedIngredientDto(
    val id: Int?,
    val original: String,
    val name: String
)
