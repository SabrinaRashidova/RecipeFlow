package com.sabrina.recipeflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sabrina.recipeflow.ui.screens.FavoritesScreen
import com.sabrina.recipeflow.ui.screens.RecipeScreen

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeSearch.route
    ){
        composable(route = Screen.RecipeSearch.route) {
            RecipeScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(Screen.RecipeDetail.createRoute(id))
                }
            )
        }

        composable(route = Screen.RecipeDetail.route) {

        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                onNavigateToDetail = {id ->
                    navController.navigate(Screen.RecipeDetail.createRoute(id))
                }
            )
        }
    }
}