package com.example.moviesapp.data.local

import com.example.moviesapp.data.local.models.response.MovieResponse
import retrofit2.http.GET

interface MoviesService {
    @GET("movie/popular")
    suspend fun getMovies(
        //TODO query's will added
    ) : MovieResponse
}