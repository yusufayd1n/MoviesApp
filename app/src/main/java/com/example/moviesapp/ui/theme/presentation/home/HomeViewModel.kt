package com.example.moviesapp.ui.theme.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResponse
import com.example.moviesapp.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<ApiResult<MovieResponse>>(ApiResult.Loading)
    val moviesState: StateFlow<ApiResult<MovieResponse>> = _movies

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMoviesUseCase.invoke().collect {
                _movies.value = it
            }
        }
    }

}