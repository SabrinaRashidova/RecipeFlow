package com.sabrina.recipeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sabrina.recipeflow.presentation.intent.RecipeDetailIntent
import com.sabrina.recipeflow.presentation.viewmodel.RecipeDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val orangePrimary = Color(0xFFFF6D00)
    val backgroundColor = Color(0xFFFFFBF0)

    Box(modifier = Modifier.fillMaxSize().background(backgroundColor)){
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = orangePrimary)
        } else if (state.error != null){
            Text(text = state.error!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        }else{
            state.recipe?.let {recipe->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(modifier = Modifier.fillMaxWidth().height(320.dp)){
                        AsyncImage(
                            model = recipe.imageUrl,
                            contentDescription = recipe.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.background(Color.White,CircleShape).size(40.dp)
                            ) {
                                Icon(Icons.Default.ArrowBack,contentDescription = "back", tint = Color.Black)
                            }
                            IconButton(
                                onClick = {viewModel.onIntent(RecipeDetailIntent.ToggleFavorite)},
                                modifier = Modifier.background(Color.White,CircleShape).size(40.dp)
                            ) {
                                Icon(imageVector = if (recipe.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black)
                            }
                        }

                        Column(
                            modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)
                        ) {
                            Row {
                                Badge(containerColor = orangePrimary, contentColor = Color.White){
                                    Text("Asian", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                                }
                                Spacer(Modifier.width(8.dp))
                                Badge(containerColor = Color.White, contentColor = Color.Black) {
                                    Text("Easy", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                                }
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = recipe.title,
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 34.sp
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard(Modifier.weight(1f), Icons.Default.Schedule,"Cook Time", "${recipe.readyInMinutes} min")
                        InfoCard(Modifier.weight(1f), Icons.Default.Groups, "Servings", "${recipe.servings} people")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard(Modifier.weight(1f), Icons.Default.Star, "Difficulty", "Easy")
                        InfoCard(Modifier.weight(1f), Icons.Default.Whatshot, "Calories", "380 kcal")
                    }

                    Card(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.RestaurantMenu, contentDescription = null, tint = orangePrimary)
                                Spacer(Modifier.width(8.dp))
                                Text("Ingredients", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(12.dp))
                            recipe.ingredients.forEach { ingredient ->
                                IngredientItem(ingredient)
                            }
                        }
                    }

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Instructions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(12.dp))

                        recipe.instructions.split("\n").filter { it.isNotBlank() }.forEachIndexed { index,step->
                            InstructionStep(index+1,step)
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }

                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = orangePrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Back to Recipes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun InfoCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String
){
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon,contentDescription = null,tint = Color(0xFFFFAB40), modifier = Modifier.size(24.dp))
            Text(label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun IngredientItem(name: String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp).fillMaxWidth()
            .background(Color(0xFFFFF3E0), RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        Box(modifier = Modifier.size(6.dp).background(Color(0xFFFF6D00), CircleShape))
        Spacer(Modifier.width(12.dp))
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun InstructionStep(number: Int,text: String){
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Box(
            modifier = Modifier.size(32.dp).background(Color(0xFFFF6D00), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = number.toString(), color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
    }
}