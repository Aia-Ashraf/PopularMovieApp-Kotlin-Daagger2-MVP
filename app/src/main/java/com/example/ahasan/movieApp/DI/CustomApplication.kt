package com.example.ahasan.movieApp.DI

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import com.example.ahasan.movieApp.DI.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class CustomApplication : Application(), HasActivityInjector, HasServiceInjector {
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    companion object {
        fun get(context: Context): CustomApplication {
            return context.applicationContext as CustomApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }
}