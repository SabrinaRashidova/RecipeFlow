package com.sabrina.recipeflow.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sabrina.recipeflow.ui.screens.BrandOrange

@Composable
fun SelectedIngredientsSection(
    ingredients: List<String>,
    onRemove: (String) -> Unit,
    onClearAll: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Your Ingredients (${ingredients.size})", fontWeight = FontWeight.Bold)
                TextButton(onClick = onClearAll) { Text("Clear All", color = BrandOrange) }
            }

            IngredientChips(ingredients = ingredients, onRemove = onRemove)
        }
    }
}