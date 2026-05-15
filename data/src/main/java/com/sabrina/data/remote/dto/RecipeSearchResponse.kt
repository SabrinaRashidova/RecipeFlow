package com.sabrina.data.remote.dto

data class RecipeSearchResponse(
    val results: List<RecipeDto>,
    val number: Int,
    val offset: Int,
    val totalResults: Int
)
