package com.sabrina.recipeflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabrina.recipeflow.presentation.viewmodel.FavoritesViewModel
import com.sabrina.recipeflow.ui.components.EmptyState
import com.sabrina.recipeflow.ui.components.RecipeGrid

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit
){
    val favorites by viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            EmptyState(
                message = "You haven't saved any recipes yet!",
                icon = Icons.Default.FavoriteBorder
            )
        }else {
            RecipeGrid(
                recipes = favorites,
                onFavoriteClick = { viewModel.onToggleFavorite(it) },
                onRecipeClick = { onNavigateToDetail(it) }
            )
        }
    }
}