package com.sabrina.domain.repository

import com.sabrina.domain.model.Recipe

interface RecipeRepository {
    suspend fun searchRecipesByIngredients(
        ingredients: List<String>
    ) : Result<List<Recipe>>

    suspend fun toggleFavorite(recipe: Recipe)
}