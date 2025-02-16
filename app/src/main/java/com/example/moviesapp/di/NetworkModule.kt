package com.example.moviesapp.di

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.remote.api.MoviesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO access token will storage more secure way
private const val ACCESS_TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MzM5YWI1MjQxYmYwYzk1OWRkMjczZGU0N2QzMDczNSIsIm5iZiI6MTcyMzk4NDA2Mi4yMTIsInN1YiI6IjY2YzFlOGJlMjc3Y2NmOTU3ZGEyNzAxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jl1NZFmPe9f4Rek7Px_F8Mn_2Te1bxY-j2PSjhDKS4A\n"

val networkModule = module {
    single {
        Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(newRequest)
        }
    }

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(logging)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MoviesService::class.java) }
}