package com.sabrina.recipeflow.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabrina.recipeflow.presentation.intent.RecipeIntent
import com.sabrina.recipeflow.presentation.viewmodel.RecipeViewModel
import com.sabrina.recipeflow.ui.components.EmptyState
import com.sabrina.recipeflow.ui.components.IngredientChips
import com.sabrina.recipeflow.ui.components.IngredientInput
import com.sabrina.recipeflow.ui.components.RecipeGrid

@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "RecipeFlow",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        IngredientInput(
            query = state.searchQuery,
            onQueryChange = { viewModel.onIntent(RecipeIntent.EnteredIngredient(it)) },
            onAddClick = { viewModel.onIntent(RecipeIntent.AddIngredient) }
        )

        IngredientChips(
            ingredients = state.ingredients,
            onRemove = { viewModel.onIntent(RecipeIntent.RemoveIngredient(it)) }
        )

        Button(
            onClick = { viewModel.onIntent(RecipeIntent.SearchRecipes) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.ingredients.isNotEmpty() && !state.isLoading
        ) {
            Text("Find Recipes")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when{
                state.isLoading ->{
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null ->{
                    EmptyState(
                        message = state.error ?: "An unexpected error occurred",
                        icon = Icons.Default.Warning,
                        onActionClick = { viewModel.onIntent(RecipeIntent.SearchRecipes) }
                    )
                }
                state.recipes.isEmpty() && state.ingredients.isEmpty() -> {
                    EmptyState(
                        message = "Add some ingredients to get started!",
                        icon = Icons.Default.Search
                    )
                }
                state.recipes.isEmpty() && !state.isLoading -> {
                    EmptyState(
                        message = "We couldn't find any recipes with those ingredients.",
                        icon = Icons.Default.Info
                    )
                }
                else -> {
                    RecipeGrid(
                        recipes = state.recipes,
                        onFavoriteClick = { viewModel.onIntent(RecipeIntent.ToggleFavorite(it)) }
                    )
                }
            }
        }
    }
}