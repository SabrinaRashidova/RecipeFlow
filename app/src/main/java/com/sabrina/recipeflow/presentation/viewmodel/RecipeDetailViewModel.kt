package com.sabrina.recipeflow.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.domain.repository.RecipeRepository
import com.sabrina.recipeflow.presentation.state.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val recipeId: Int = checkNotNull(savedStateHandle["recipeId"])

    private val _state = MutableStateFlow(RecipeDetailState())
    val state = _state.asStateFlow()

    init {

    }

    private fun fetchDetails(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getRecipeDetails(recipeId)
                .onSuccess { details -> _state.update { it.copy(recipe = details, isLoading = false) } }
                .onFailure { e -> _state.update { it.copy(error = e.message, isLoading = false) } }
        }
    }
}