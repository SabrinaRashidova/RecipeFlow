package com.sabrina.domain.repository

import com.sabrina.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun searchRecipesByIngredients(
        ingredients: List<String>
    ) : Result<List<Recipe>>

    suspend fun toggleFavorite(recipe: Recipe)

    fun getFavoriteRecipes(): Flow<List<Recipe>>
}