package com.example.ahasan.movieApp.DI.Modules

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.example.ahasan.movieApp.DI.ViewModelFactory.DetailsViewModelModule
import com.example.ahasan.movieApp.Interfaces.ApiInterface
import com.example.ahasan.movieApp.Interfaces.TopRatedAPI
import com.example.ahasan.movieApp.MainScreen.ViewModel.MovieViewModel
import com.example.ahasan.movieApp.MainScreen.View.MovieView
import com.example.ahasan.movieApp.DI.ViewModelFactory.ViewModelModule
import com.example.ahasan.movieApp.MainScreen.View.MainActivity
import com.example.ahasan.movieApp.DI.Annotations.ActivityContext
import com.example.ahasan.movieApp.DI.Annotations.RetrofitMovie
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
            TopRatedAPIModule::class,
            ViewModelModule::class,
            provideMoviewView::class,
            DetailsActivityModule::class,
            DetailsViewModelModule::class

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
    fun provideActivity(@ActivityContext movieView: MovieView): MovieView {
        return movieView
    }
}
@Module
class viewModelModule {
    @Provides
    fun provideViewModel(viewModel: MovieViewModel): MovieViewModel {
        return viewModel
    }
}

@Module
class Mpresenter {
    @Provides
    fun providePresenter(topRatedAPI: TopRatedAPI): MovieViewModel {
        return MovieViewModel(topRatedAPI)
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

