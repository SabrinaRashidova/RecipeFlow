package com.sabrina.recipeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                RecipeScreen()
            }
        }
    }
}