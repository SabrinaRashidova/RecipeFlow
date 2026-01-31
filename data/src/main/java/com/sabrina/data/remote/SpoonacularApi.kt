package com.sabrina.data.remote

import com.sabrina.data.remote.dto.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/findByIngredients")
    suspend fun findByIngredients(
        @Query("apiKey") apiKey: String,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = 10
    ) : List<RecipeDto>
}