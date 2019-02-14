package com.example.ahasan.kotlinmovieapp

import android.util.Log
import com.example.ahasan.kotlinmovieapp.Interfaces.MovieReviewAPI
import com.example.ahasan.kotlinmovieapp.Interfaces.Movietrailers
import com.example.ahasan.kotlinmovieapp.Models.ResultX
import com.example.ahasan.kotlinmovieapp.Models.ResultXX
import com.example.ahasan.kotlinmovieapp.Models.ReviewsModel
import com.example.ahasan.kotlinmovieapp.Models.VideosModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val movieReviewAPI: MovieReviewAPI,
    private val movietrailers: Movietrailers) {



    lateinit var detailsView: DetailsView

    var reviewstList: List<ResultX>? = ArrayList()
    var trailersList: List<ResultXX>? = ArrayList()


    fun getMovieReviewAPI(id:String) {
        movieReviewAPI.getMovieReview(id, "11af5a2e9dda5914db6389d51f4a1e9f")
            ?.enqueue(object :
                Callback<ReviewsModel> {
                override fun onResponse(call: Call<ReviewsModel>?, response: Response<ReviewsModel>?) {
                    try {
                        if (response?.code() == 200) {
                            reviewstList = response.body().results
                            detailsView.setResult(reviewstList!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                override fun onFailure(call: Call<ReviewsModel>?, t: Throwable?) {
                    Log.e("MainActivityLog", t.toString())
                }
            }
            )
    }


    fun getMovieVideosAPI(id:String) {
        movietrailers.getMovieTrailers(id, "11af5a2e9dda5914db6389d51f4a1e9f")
            ?.enqueue(object :
                Callback<VideosModel> {
                override fun onResponse(call: Call<VideosModel>?, response: Response<VideosModel>?) {
                    try {
                        if (response?.code() == 200) {
                            trailersList = response.body().results
                            detailsView.setTrailerResult(trailersList!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                override fun onFailure(call: Call<VideosModel>?, t: Throwable?) {
                    Log.e("MainActivityLog", t.toString())
                }
            }
            )
    }
}