package com.example.moviesapp.data.remote.repository

import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.api.MoviesService
import com.example.moviesapp.data.remote.models.response.MovieResponse
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val moviesService: MoviesService
) : MoviesRepository {
    override suspend fun getMovies(): Flow<ApiResult<MovieResponse>> = flow {
        emit(ApiResult.Loading)
        try {
            emit(ApiResult.Success(moviesService.getMovies()))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }
}