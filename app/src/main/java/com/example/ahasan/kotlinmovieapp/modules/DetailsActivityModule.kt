package com.example.ahasan.kotlinmovieapp.modules

import android.content.Context
import com.example.ahasan.kotlinmovieapp.Interfaces.MovieReviewAPI
import com.example.ahasan.kotlinmovieapp.Interfaces.Movietrailers
import com.example.ahasan.kotlinmovieapp.activites.DetailsActivity
import com.example.ahasan.kotlinmovieapp.annotations.ActivityContext
import com.example.ahasan.kotlinmovieapp.annotations.RetrofitMovie
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
internal abstract class DetailsActivityModule {
    @ContributesAndroidInjector
        (
        modules = arrayOf(
            provideDetailsActivityContext::class,
            RetrofitReviewModule::class,
            RetrofitVideosModule::class
        )
    )
    internal abstract fun contributeMainActivity(): DetailsActivity
}

@Module
class provideDetailsActivityContext {
    @Provides
    @ActivityContext
    fun provideActivityContext(activity: DetailsActivity): Context {
        return activity
    }
}

@Module
class RetrofitReviewModule {
    @Provides
    fun getReviewResponce(@RetrofitMovie retrofit: Retrofit): MovieReviewAPI {
        return retrofit.create(MovieReviewAPI::class.java)
    }
}

@Module
class RetrofitVideosModule {
    @Provides
    fun getVideosResponce(@RetrofitMovie retrofit: Retrofit): Movietrailers {
        return retrofit.create(Movietrailers::class.java)
    }
}




