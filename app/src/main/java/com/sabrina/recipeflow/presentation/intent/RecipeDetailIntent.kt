package com.sabrina.recipeflow.presentation.intent

sealed class RecipeDetailIntent {
    data class LoadRecipe(val id: Int) : RecipeDetailIntent()
    object ToggleFavorite: RecipeDetailIntent()
}