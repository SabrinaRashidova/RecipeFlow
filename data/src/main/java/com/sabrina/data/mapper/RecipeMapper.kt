package com.sabrina.data.mapper

import com.sabrina.data.local.entity.FavoriteRecipeEntity
import com.sabrina.data.remote.dto.RecipeDto
import com.sabrina.domain.model.Recipe

fun RecipeDto.toDomain() : Recipe{
    return Recipe(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        cuisine = cuisines?.firstOrNull() ?: "General",
        cookingTime = "${readyInMinutes ?: 0} min",
        servings = servings ?: 0,
        difficulty = when {
            (readyInMinutes ?: 0) < 30 -> "Easy"
            (readyInMinutes ?: 0) < 60 -> "Medium"
            else -> "Hard"
        },
        usedIngredientCount = this.usedIngredientCount ?: 0,
        missedIngredientCount = this.missedIngredientCount ?: 0,
        isFavorite = false
    )
}

fun FavoriteRecipeEntity.toDomain() : Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        cuisine = this.cuisine,
        cookingTime = this.cookingTime,
        servings = this.servings,
        difficulty = this.difficulty,
        usedIngredientCount = this.usedIngredientCount,
        missedIngredientCount = this.missedIngredientCount,
        isFavorite = true
    )
}

fun Recipe.toEntity(): FavoriteRecipeEntity {
    return FavoriteRecipeEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        cuisine = cuisine,
        cookingTime = cookingTime,
        servings = servings,
        difficulty = difficulty,
        usedIngredientCount = usedIngredientCount,
        missedIngredientCount = missedIngredientCount
    )
}