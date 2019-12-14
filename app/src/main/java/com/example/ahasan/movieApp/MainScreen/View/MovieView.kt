package com.example.ahasan.movieApp.MainScreen.View

import android.arch.lifecycle.MutableLiveData
import com.example.ahasan.movieApp.MainScreen.Model.MovieList


interface MovieView {

    fun setResult(retrofitList: List<MovieList>) {
    }
}