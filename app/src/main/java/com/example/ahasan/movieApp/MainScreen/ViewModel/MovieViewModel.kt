package com.example.ahasan.movieApp.MainScreen.ViewModel

import android.arch.lifecycle.ViewModel
import com.example.ahasan.movieApp.Interfaces.TopRatedAPI
import com.example.ahasan.movieApp.MainScreen.Model.MovieList
import com.example.ahasan.movieApp.MainScreen.Model.Movies
import com.example.ahasan.movieApp.MainScreen.View.MovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val topRatedAPI: TopRatedAPI
): ViewModel() {

    var retrofitList: List<MovieList>? = ArrayList()
    var disposable = CompositeDisposable();
        lateinit var movieView: MovieView



    fun getRetrofitTopRelated() {

        disposable?.add(topRatedAPI.getTopRated("11af5a2e9dda5914db6389d51f4a1e9f")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ t: Movies? ->
                retrofitList = t?.movieList; movieView.setResult(retrofitList!!)
            })
        )
    }
}