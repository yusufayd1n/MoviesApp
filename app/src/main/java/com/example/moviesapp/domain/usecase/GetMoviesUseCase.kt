package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResponse
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(): Flow<ApiResult<MovieResponse>> {
        return moviesRepository.getMovies()
    }
}