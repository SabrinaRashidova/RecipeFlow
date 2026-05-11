package com.sabrina.domain.repository

import com.sabrina.domain.model.Recipe
import com.sabrina.domain.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun searchRecipesByIngredients(
        ingredients: List<String>
    ) : Result<List<Recipe>>

    suspend fun isFavoriteLocal(id: Int): Boolean

    suspend fun toggleFavorite(recipe: Recipe)

    suspend fun toggleFavoriteDetail(recipe: RecipeDetail)

    fun getFavoriteRecipes(): Flow<List<Recipe>>

    suspend fun getRecipeDetails(id: Int) : Result<RecipeDetail>
}