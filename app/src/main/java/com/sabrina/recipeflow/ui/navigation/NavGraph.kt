package com.sabrina.recipeflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sabrina.recipeflow.ui.screens.FavoritesScreen
import com.sabrina.recipeflow.ui.screens.RecipeDetailScreen
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

        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) {
            RecipeDetailScreen(onBackClick = { navController.popBackStack() })
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