package com.sabrina.recipeflow.presentation.state

import com.sabrina.domain.model.RecipeDetail

data class RecipeDetailState(
    val recipe: RecipeDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
