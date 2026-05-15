package com.sabrina.data.remote

import com.sabrina.data.remote.dto.RecipeDetailDto
import com.sabrina.data.remote.dto.RecipeDto
import com.sabrina.data.remote.dto.RecipeSearchResponse
import com.sabrina.data.remote.dto.RecipeSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApi {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("includeIngredients") ingredients: String,
        @Query("addRecipeInformation") addRecipeInfo: Boolean = true,
        @Query("fillIngredients") fillIngredients: Boolean = true,
        @Query("number") number: Int = 10
    ) : RecipeSearchResponseDto

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ) : RecipeDetailDto

}