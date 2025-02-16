package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.networkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule)
        }
    }
}