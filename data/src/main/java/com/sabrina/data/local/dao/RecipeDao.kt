package com.sabrina.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabrina.data.local.entity.FavoriteRecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(recipe : FavoriteRecipeEntity)

    @Delete
    suspend fun deleteFavorite(recipe : FavoriteRecipeEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_recipes WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}