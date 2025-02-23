package com.example.moviesapp.ui.theme.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResult
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesHomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = koinViewModel()

    val moviesState by viewModel.moviesState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    when (val response = moviesState) {
        is ApiResult.Error -> {

        }

        ApiResult.Loading -> {

        }

        is ApiResult.Success -> {
            response.data.results.let { movieList ->
                LazyColumn {
                    items(movieList) { movie ->
                        MovieItem(movie) {

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: MovieResult, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clickable {
            onClick.invoke()
        }) {
        val imageUrl = "https://image.tmdb.org/t/p/w300${movie.poster_path}"
        GlideImage(
            model = imageUrl,
            contentDescription = "Image from URL",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .height(128.dp)
                .padding(start = 4.dp)
        ) {
            Text("YUSUFAYDIN", color = Color.Black)
        }
    }
}

