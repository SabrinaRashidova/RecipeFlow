package com.sabrina.recipeflow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabrina.domain.model.Recipe
import com.sabrina.recipeflow.ui.screens.BrandOrange
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.FavoriteBorder
import coil.compose.AsyncImage

@Composable
fun RecipeCard(
    recipe: Recipe,
    onFavoriteClick: (Recipe) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )

                Surface(
                    modifier = Modifier.padding(12.dp),
                    color = BrandOrange,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = recipe.cuisine,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Surface(
                    onClick = { onFavoriteClick(recipe) },
                    modifier = Modifier
                        .padding(12.dp)
                        .size(40.dp)
                        .align(Alignment.TopEnd),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Icon(imageVector = if (recipe.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black, modifier = Modifier.padding(8.dp))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RecipeDetailIconText(Icons.Default.Schedule, recipe.cookingTime)
                    RecipeDetailIconText(Icons.Default.SoupKitchen, text = "${recipe.servings}")
                    RecipeDetailIconText(Icons.Default.Star, recipe.difficulty, tint = Color(0xFFFFD600))
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.LightGray.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "${recipe.usedIngredientCount} ingredients needed",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun RecipeDetailIconText(
    icon: ImageVector,
    text: String,
    tint: Color = Color.Gray
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}