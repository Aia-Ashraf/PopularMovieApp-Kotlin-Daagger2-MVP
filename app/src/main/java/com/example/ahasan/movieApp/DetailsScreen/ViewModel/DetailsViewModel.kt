package com.example.ahasan.movieApp.DetailsScreen.ViewModel

import android.arch.lifecycle.ViewModel
import com.example.ahasan.movieApp.DetailsScreen.Model.GenreModel
import com.example.ahasan.movieApp.Interfaces.MovieDetailsAPI
import com.example.ahasan.movieApp.DetailsScreen.Model.DetailsModel
import com.example.ahasan.movieApp.DetailsScreen.View.DetailsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val movieDetailsAPI: MovieDetailsAPI): ViewModel() {
    lateinit var detailsView: DetailsView
    var disposable = CompositeDisposable();

    fun getMovieReviewAPI(id:String) {
        disposable?.add(movieDetailsAPI.getMovieDetails(id,"11af5a2e9dda5914db6389d51f4a1e9f")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ t: DetailsModel? -> detailsView.setResult(t!!)
            }
            )
        )
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}