package com.sabrina.data.mapper

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