package com.sabrina.recipeflow.presentation.intent

import com.sabrina.domain.model.Recipe

sealed class RecipeIntent {
    data class EnteredIngredient(val name: String) : RecipeIntent()
    object AddIngredient: RecipeIntent()
    data class RemoveIngredient(val name: String) : RecipeIntent()
    object SearchRecipes: RecipeIntent()
    data class ToggleFavorite(val recipe: Recipe) : RecipeIntent()
}