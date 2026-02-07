package com.sabrina.recipeflow.state

import com.sabrina.domain.model.Recipe

data class RecipeState(
    val ingredients: List<String> = emptyList(),
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)