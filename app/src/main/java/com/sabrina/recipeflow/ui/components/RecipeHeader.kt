package com.sabrina.recipeflow.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabrina.recipeflow.ui.screens.BrandOrange

@Composable
fun RecipeHeader(favoriteCount: Int, onFavoritesClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Recipe Finder", color = BrandOrange, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Surface(
            onClick = onFavoritesClick,
            color = Color(0xFFFFEBEE),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red )
                Spacer(Modifier.width(4.dp))
                Text("Favorites ($favoriteCount)", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}