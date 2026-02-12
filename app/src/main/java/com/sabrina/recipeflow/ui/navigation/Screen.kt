package com.sabrina.recipeflow.ui.navigation

sealed class Screen(val route: String) {
    object RecipeSearch: Screen("recipe_search")
    object RecipeDetail: Screen("recipe_detail/{recipeId}"){
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
    }
    object Favorites : Screen("favorites")
}