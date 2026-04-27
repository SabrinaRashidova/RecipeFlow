package com.sabrina.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val cuisine: String,
    val cookingTime: String,
    val servings: Int,
    val difficulty: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int
)