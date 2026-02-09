package com.sabrina.recipeflow.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.sabrina.recipeflow.screens.intent.RecipeIntent
import com.sabrina.recipeflow.screens.viewmodel.RecipeViewModel

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
            onAddClick = { viewModel.onIntent(RecipeIntent.AddIngredient)}
        )

        IngredientChips(
            ingredients = state.ingredients,
            onRemove = { viewModel.onIntent(RecipeIntent.RemoveIngredient(it))}
        )

        Button(
            onClick = { viewModel.onIntent(RecipeIntent.SearchRecipes) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.ingredients.isNotEmpty() && !state.isLoading
        ) {
            Text("Find Recipes")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null){
                Text(state.error!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else{
                RecipeGrid(
                    recipes = state.recipes,
                    onFavoriteClick = { viewModel.onIntent(RecipeIntent.ToggleFavorite(it)) }
                )
            }
        }
    }
}