package com.example.ahasan.movieApp.DI.Components

import android.app.Application
import android.content.Context
import com.example.ahasan.movieApp.DI.CustomApplication
import com.example.ahasan.movieApp.DI.ViewModelFactory.DetailsViewModelModule
import com.example.ahasan.movieApp.DI.ViewModelFactory.ViewModelModule
import com.example.ahasan.movieApp.DI.Modules.*
import com.example.ahasan.movieApp.DI.Modules.ActivityModule
import com.example.ahasan.movieApp.DI.Modules.DetailsActivityModule
import com.example.ahasan.movieApp.DI.Annotations.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        ApplicationModule::class,
        RetrofitObjectModule::class,
        ActivityModule::class,
        DetailsActivityModule::class,
        ViewModelModule::class,
        TopRatedAPIModule::class,
        DetailsViewModelModule::class,
        RetrofitReviewModule::class
    )
)
interface ApplicationComponent {
    //    fun getDao(): WordDao
    @ApplicationContext
    fun getApplicationContext(): Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

    fun inject(customApplicationNew: CustomApplication)

}