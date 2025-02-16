package com.example.moviesapp.di

import com.example.moviesapp.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetMoviesUseCase> { GetMoviesUseCase(get()) }
}