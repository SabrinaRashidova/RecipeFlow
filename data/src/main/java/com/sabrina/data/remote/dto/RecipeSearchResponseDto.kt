package com.sabrina.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponseDto(
    @SerializedName("results")
    val results: List<RecipeDto>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("totalResults")
    val totalResults: Int
)
