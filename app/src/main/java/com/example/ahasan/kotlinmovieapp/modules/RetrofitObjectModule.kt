package com.example.ahasan.kotlinmovieapp.modules

import com.example.ahasan.kotlinmovieapp.annotations.RetrofitMovie
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitObjectModule {
    @Singleton
    @Provides
    @RetrofitMovie
    fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}