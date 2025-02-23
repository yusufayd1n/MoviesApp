package com.example.moviesapp.di

import com.example.moviesapp.ui.theme.presentation.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<HomeViewModel> { HomeViewModel(get()) }
}