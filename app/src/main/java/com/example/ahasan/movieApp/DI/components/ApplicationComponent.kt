package com.example.ahasan.movieApp.DI.components

import android.app.Application
import android.content.Context
import com.example.ahasan.movieApp.DI.CustomApplication
import com.example.ahasan.movieApp.DI.DetailsViewModelModule
import com.example.ahasan.movieApp.DI.ViewModelModule
import com.example.ahasan.movieApp.DI.modules.*
import com.example.ahasan.movieApp.DI.modules.ActivityModule
import com.example.ahasan.movieApp.DI.modules.DetailsActivityModule
import com.example.ahasan.movieApp.DI.annotations.ApplicationContext
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