package com.sabrina.recipeflow.ui.navigation

sealed class Screen(val route: String) {
    object RecipeSearch: Screen("recipe_search")
    object RecipeDetail: Screen("recipe_detail/{recipeId}"){
        fun createRoute(id: Int) = "recipe_detail/$id"
    }
    object Favorites : Screen("favorites")
    object Welcome : Screen("welcome")
}