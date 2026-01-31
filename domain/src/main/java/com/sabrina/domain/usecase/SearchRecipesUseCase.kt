package com.sabrina.domain.usecase

import com.sabrina.domain.model.Ingredient
import com.sabrina.domain.model.Recipe
import com.sabrina.domain.repository.RecipeRepository

class SearchRecipesUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(ingredients: List<String>) : Result<List<Recipe>> {
        if (ingredients.isEmpty()){
            return Result.failure(Exception("Please add at least one ingredient"))
        }
        return repository.searchRecipesByIngredients(ingredients)
    }
}