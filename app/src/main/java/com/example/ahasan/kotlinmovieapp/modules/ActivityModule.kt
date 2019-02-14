package com.example.ahasan.kotlinmovieapp.modules

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.example.ahasan.kotlinmovieapp.Interfaces.ApiInterface
import com.example.ahasan.kotlinmovieapp.Interfaces.MovieReviewAPI
import com.example.ahasan.kotlinmovieapp.Interfaces.TopRatedAPI
import com.example.ahasan.kotlinmovieapp.MoviePresenter
import com.example.ahasan.kotlinmovieapp.MovieView
import com.example.ahasan.kotlinmovieapp.activites.MainActivity
import com.example.ahasan.kotlinmovieapp.annotations.ActivityContext
import com.example.ahasan.kotlinmovieapp.annotations.RetrofitMovie
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
        (
        modules = arrayOf(
            RetrofitMovieModule::class,
            LayoutManagerModule::class,
            provideMainActivityContext::class,
            TopRatedAPIModule::class
        )
    )
    internal abstract fun contributeMainActivity(): MainActivity
}

@Module
class provideMainActivityContext {
    @Provides
    @ActivityContext
    fun provideActivityContext(activity: MainActivity): Context {
        return activity
    }
}

@Module
class provideMoviewView {
    @Provides
    fun provideActivity(movieView: MovieView): MovieView {
        return movieView
    }
}

@Module
class Mpresenter {
    @Provides
    fun providePresenter(apiInterface: ApiInterface ,topRatedAPI: TopRatedAPI): MoviePresenter {
        return MoviePresenter(apiInterface,topRatedAPI)
    }
}

@Module
class RetrofitMovieModule {
    @Provides
    fun getResponce(@RetrofitMovie retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}

@Module
class LayoutManagerModule {

    @Provides
    fun provideLayoutManager(@ActivityContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2)
    }
}

@Module
class TopRatedAPIModule {

    @Provides
    fun provideTopRatedAPI(@RetrofitMovie retrofit: Retrofit): TopRatedAPI {
        return retrofit.create(TopRatedAPI::class.java)
    }
}

