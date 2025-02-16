package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies() : Flow<ApiResult<MovieResponse>>
}