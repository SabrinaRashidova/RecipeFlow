package com.sabrina.recipeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.sabrina.recipeflow.ui.navigation.SetupNavGraph
import com.sabrina.recipeflow.ui.screens.RecipeScreen
import com.sabrina.recipeflow.ui.theme.RecipeFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFlowTheme {
               val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}