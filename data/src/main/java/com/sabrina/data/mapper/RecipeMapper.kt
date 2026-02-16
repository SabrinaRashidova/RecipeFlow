package com.sabrina.data.mapper

import com.sabrina.data.local.entity.FavoriteRecipeEntity
import com.sabrina.data.remote.dto.RecipeDto
import com.sabrina.domain.model.Recipe

fun RecipeDto.toDomain() : Recipe{
    return Recipe(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        usedIngredientCount = this.usedIngredientCount,
        missedIngredientCount = this.missedIngredientCount
    )
}

fun FavoriteRecipeEntity.toDomain() : Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        usedIngredientCount = 0,
        missedIngredientCount = 0,
        isFavorite = true
    )
}