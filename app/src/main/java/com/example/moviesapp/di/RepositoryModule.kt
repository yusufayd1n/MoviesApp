package com.example.moviesapp.di

import com.example.moviesapp.data.remote.repository.MoviesRepositoryImpl
import com.example.moviesapp.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}