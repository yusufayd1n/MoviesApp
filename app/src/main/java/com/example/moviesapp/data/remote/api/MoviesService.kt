package com.example.moviesapp.data.remote.api

import com.example.moviesapp.data.remote.models.response.MovieResponse
import retrofit2.http.GET

interface MoviesService {
    @GET("movie/popular")
    suspend fun getMovies(
        //TODO query's will added
    ) : MovieResponse
}