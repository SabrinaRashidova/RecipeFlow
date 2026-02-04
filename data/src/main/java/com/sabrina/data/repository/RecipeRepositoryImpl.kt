package com.sabrina.data.repository

import com.sabrina.data.local.dao.RecipeDao
import com.sabrina.data.local.entity.FavoriteRecipeEntity
import com.sabrina.data.mapper.toDomain
import com.sabrina.data.remote.SpoonacularApi
import com.sabrina.domain.model.Recipe
import com.sabrina.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: SpoonacularApi,
    private val recipeDao: RecipeDao,
    private val apiKey: String
) : RecipeRepository{
    override suspend fun searchRecipesByIngredients(ingredients: List<String>): Result<List<Recipe>> {
        return try {
            val ingredientString = ingredients.joinToString(",")
            val response = api.findByIngredients(apiKey,ingredientString)
            Result.success(response.map { it.toDomain() })
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun toggleFavorite(recipe: Recipe) {
        val entity = FavoriteRecipeEntity(
            id = recipe.id,
            title = recipe.title,
            imageUrl = recipe.imageUrl
        )

        val isCurrentlyFavorite = recipeDao.isFavorite(recipe.id)

        if (isCurrentlyFavorite) {
            recipeDao.deleteFavorite(entity)
        } else {
            recipeDao.insertFavorite(entity)
        }
    }
}