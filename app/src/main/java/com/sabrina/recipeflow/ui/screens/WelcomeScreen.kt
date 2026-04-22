package com.sabrina.recipeflow.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabrina.recipeflow.ui.screens.auth.AuthViewModel

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to RecipeFlow!", style = MaterialTheme.typography.headlineLarge)
        Text("Discover amazing recipes instantly.", textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(28.dp))

        Button(onClick = onGetStarted){
            Text("Get Started")
        }
    }
}