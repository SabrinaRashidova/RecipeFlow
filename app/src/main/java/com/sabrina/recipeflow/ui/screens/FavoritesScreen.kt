package com.sabrina.recipeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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
import com.sabrina.recipeflow.presentation.viewmodel.FavoritesViewModel
import com.sabrina.recipeflow.ui.components.EmptyState
import com.sabrina.recipeflow.ui.components.RecipeCard

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val favorites by viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Color(0xFFFFCC80),
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "My Favorites",
                style = MaterialTheme.typography.titleMedium,
                color = BrandOrange,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            if (favorites.isNotEmpty()) {
                item {
                    Text(
                        text = "Saved Recipes",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(
                        text = "You have ${favorites.size} favorite recipes",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            }

            if (favorites.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight(), contentAlignment = Alignment.Center) {
                        EmptyState(
                            message = "Your favorites list is empty.\nTap the heart on any recipe to save it!",
                            icon = Icons.Default.Favorite
                        )
                    }
                }
            } else {
                items(favorites) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onFavoriteClick = { viewModel.onToggleFavorite(recipe) },
                        onClick = { onNavigateToDetail(recipe.id) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}