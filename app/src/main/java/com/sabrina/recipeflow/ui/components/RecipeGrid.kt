package com.sabrina.recipeflow.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sabrina.domain.model.Recipe

@Composable
fun RecipeGrid(recipes: List<Recipe>, onFavoriteClick: (Recipe) -> Unit){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(recipes) {recipe ->
            RecipeCard(recipe = recipe, onFavoriteClick = onFavoriteClick)
        }
    }
}