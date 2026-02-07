package com.sabrina.recipeflow.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.domain.repository.RecipeRepository
import com.sabrina.domain.usecase.SearchRecipesUseCase
import com.sabrina.recipeflow.screens.intent.RecipeIntent
import com.sabrina.recipeflow.screens.state.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                viewModelScope.launch {
                    repository.toggleFavorite(intent.recipe)
                }
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            searchRecipesUseCase(_state.value.ingredients)
                .onSuccess { list ->
                    _state.update { it.copy(recipes = list, isLoading = false) }
                }
                .onFailure { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }
}