package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.moviesapp.ui.theme.MoviesAppTheme
import com.example.moviesapp.ui.theme.presentation.home.HomeViewModel
import com.example.moviesapp.ui.theme.presentation.home.MoviesHomeScreen
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                enableEdgeToEdge()
                MoviesHomeScreen()
            }
        }
    }
}
