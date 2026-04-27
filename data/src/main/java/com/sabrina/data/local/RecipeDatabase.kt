package com.sabrina.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabrina.data.local.dao.RecipeDao
import com.sabrina.data.local.entity.FavoriteRecipeEntity

@Database(
    entities = [FavoriteRecipeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao

    companion object {
        const val DATABASE_NAME = "recipe_flow_db"
    }
}