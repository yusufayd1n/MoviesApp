package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.networkModule
import com.example.moviesapp.di.repositoryModule
import com.example.moviesapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(
                networkModule,
                repositoryModule,
                useCaseModule
            ))
        }
    }
}