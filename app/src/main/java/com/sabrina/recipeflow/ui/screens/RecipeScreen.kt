package com.sabrina.recipeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
import com.sabrina.recipeflow.ui.components.*

@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        RecipeHeader(
            favoriteCount = 0,
            onFavoritesClick = onNavigateToFavorites
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            if (state.recipes.isEmpty() && !state.isLoading) {
                item {
                    Text(
                        text = "What's in your kitchen?",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Add ingredients to discover amazing recipes you can make",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    IngredientInput(
                        query = state.searchQuery,
                        onQueryChange = { viewModel.onIntent(RecipeIntent.EnteredIngredient(it)) },
                        onAddClick = { viewModel.onIntent(RecipeIntent.AddIngredient) }
                    )
                }

                if (state.ingredients.isNotEmpty()) {
                    item {
                        SelectedIngredientsSection(
                            ingredients = state.ingredients,
                            onRemove = { viewModel.onIntent(RecipeIntent.RemoveIngredient(it)) },
                            onClearAll = {}
                        )
                    }
                }

                item {
                    PopularIngredientsSection(
                        onIngredientClick = { ingredient->
                            viewModel.onIntent(RecipeIntent.EnteredIngredient(ingredient))
                            viewModel.onIntent(RecipeIntent.AddIngredient)
                        }
                    )
                }
            }


            if (state.recipes.isNotEmpty() || state.isLoading) {
                item {
                    Text(
                        text = "Found ${state.recipes.size} delicious recipes",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                if (state.isLoading) {
                    item {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                } else {
                    items(state.recipes) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            onFavoriteClick = { viewModel.onIntent(RecipeIntent.ToggleFavorite(recipe)) },
                            onClick = { onNavigateToDetail(recipe.id) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

    if (state.ingredients.isNotEmpty() && state.recipes.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = { viewModel.onIntent(RecipeIntent.SearchRecipes) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandOrange),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Find Recipes with ${state.ingredients.size} ingredients")
            }
        }
    }
}