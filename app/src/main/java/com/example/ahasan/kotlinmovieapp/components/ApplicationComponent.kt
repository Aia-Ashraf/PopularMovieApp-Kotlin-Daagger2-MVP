package com.example.ahasan.kotlinmovieapp.components

import android.app.Application
import android.content.Context
import com.example.ahasan.kotlinmovieapp.CustomApplication
import com.example.ahasan.kotlinmovieapp.annotations.ApplicationContext
import com.example.ahasan.kotlinmovieapp.modules.ActivityModule
import com.example.ahasan.kotlinmovieapp.modules.ApplicationModule
import com.example.ahasan.kotlinmovieapp.modules.DetailsActivityModule
import com.example.ahasan.kotlinmovieapp.modules.RetrofitObjectModule
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
        DetailsActivityModule::class
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