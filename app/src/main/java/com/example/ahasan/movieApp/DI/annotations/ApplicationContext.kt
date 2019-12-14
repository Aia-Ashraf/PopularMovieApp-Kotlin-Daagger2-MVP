package com.example.ahasan.movieApp.DI.annotations

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationContext

