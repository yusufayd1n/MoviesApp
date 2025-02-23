package com.example.moviesapp.ui.theme.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.api.ApiResult
import com.example.moviesapp.data.remote.models.response.MovieResponse
import com.example.moviesapp.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<ApiResult<MovieResponse>>(ApiResult.Loading)
    val moviesResponse: StateFlow<ApiResult<MovieResponse>> = _movies

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke().collect {
                Log.d("YUSUFAYDIN",it.toString())
            }
        }
    }

}