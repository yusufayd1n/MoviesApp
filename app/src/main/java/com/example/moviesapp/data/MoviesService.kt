package com.example.moviesapp.data

import retrofit2.http.GET

interface MoviesService {
    @GET
    suspend fun getMovies() 
}