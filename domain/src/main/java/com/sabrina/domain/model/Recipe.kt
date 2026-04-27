package com.sabrina.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val cuisine: String,
    val cookingTime: String,
    val servings: Int,
    val difficulty: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val isFavorite: Boolean = false
){
    val totalIngredientCount: Int
        get() = usedIngredientCount + missedIngredientCount
}
