package com.sabrina.recipeflow.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.domain.model.Recipe
import com.sabrina.domain.repository.RecipeRepository
import com.sabrina.domain.usecase.SearchRecipesUseCase
import com.sabrina.recipeflow.presentation.intent.RecipeIntent
import com.sabrina.recipeflow.presentation.state.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val repository: RecipeRepository
) : ViewModel(){

    private val _state = MutableStateFlow(RecipeState())
    val state = _state.asStateFlow()

    fun onIntent(intent: RecipeIntent) {
        when(intent) {
            is RecipeIntent.EnteredIngredient -> {
                _state.update { it.copy(searchQuery = intent.name) }
            }
            is RecipeIntent.AddIngredient -> {
                val currentQuery = _state.value.searchQuery
                if (currentQuery.isNotBlank()) {
                    _state.update { it.copy(
                        ingredients = it.ingredients + currentQuery,
                        searchQuery = ""
                    ) }
                }
            }
            is RecipeIntent.RemoveIngredient -> {
                _state.update { it.copy(
                    ingredients = it.ingredients - intent.name
                ) }
            }
            is RecipeIntent.SearchRecipes -> executeSearch()
            is RecipeIntent.ToggleFavorite -> {
                toggleFavorite(intent.recipe)
            }

            is RecipeIntent.ClearSearchResults -> {
                _state.update { it.copy(recipes = emptyList()) }
            }

            is RecipeIntent.ClearAllIngredients -> {
                _state.update { it.copy(
                    ingredients = emptyList(),
                    recipes = emptyList(),
                    searchQuery = ""
                ) }
            }

            is RecipeIntent.ResetToIngredientSelection -> {
                _state.update { it.copy(recipes = emptyList()) }
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val favoriteIds = repository.getFavoriteRecipes().first().map { it.id }.toSet()

            searchRecipesUseCase(_state.value.ingredients)
                .onSuccess { networkList ->
                    val syncedList = networkList.map { recipe ->
                        if (favoriteIds.contains(recipe.id)) {
                            recipe.copy(isFavorite = true)
                        } else {
                            recipe
                        }
                    }

                    _state.update { it.copy(recipes = syncedList, isLoading = false) }
                }
                .onFailure { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }

    private fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            repository.toggleFavorite(recipe)

            val updatedRecipes = _state.value.recipes.map {
                if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
            }
            _state.update { it.copy(recipes = updatedRecipes) }
        }
    }
}