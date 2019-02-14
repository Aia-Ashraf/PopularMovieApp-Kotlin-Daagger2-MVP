package com.example.ahasan.kotlinmovieapp.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.ahasan.kotlinmovieapp.annotations.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideSharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
    }
}