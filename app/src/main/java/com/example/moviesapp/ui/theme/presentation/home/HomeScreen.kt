package com.example.moviesapp.ui.theme.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResult
import com.example.moviesapp.utils.IMAGE_BASE_URL
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesHomeScreen() {
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
                LazyColumn(

                ) {
                    items(movieList) { movie ->
                        MovieItem(movie) {
                            //TODO route to detail screen
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
        .padding(top = 4.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color = Color.Black)
        .shadow(
            elevation = 8.dp, shape = RoundedCornerShape(16.dp)
        )
        .clickable {
            onClick.invoke()
        }
    ) {
        //TODO maybe i can write some extension to return url
        GlideImage(
            model = IMAGE_BASE_URL + movie.poster_path,
            contentDescription = "Image from URL",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .padding(start = 4.dp)
        ) {
            Text(
                text = movie.title ?: "",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Serif
            )

            Text(
                text = "IMDB: ${movie.vote_average}",
                color = Color.White,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.overview ?: "",
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxHeight()
            )

        }
    }
}

