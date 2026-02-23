package com.sabrina.data.remote.dto

data class RecipeDetailDto(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val instructions: String?,
    val extendedIngredients: List<ExtendedIngredientDto>,
    val analyzedInstructions: List<AnalyzedInstructionDto>,
    val readyInMinutes: Int,
    val servings: Int,
    val healthScore: Int,
    val dishTypes: List<String>
)

data class AnalyzedInstructionDto(
    val steps: List<StepDto>
)

data class StepDto(
    val number: Int,
    val step: String
)

data class ExtendedIngredientDto(
    val id: Int?,
    val original: String,
    val name: String
)
