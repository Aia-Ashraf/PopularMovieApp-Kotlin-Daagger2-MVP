package com.example.ahasan.kotlinmovieapp

import android.util.Log
import com.example.ahasan.kotlinmovieapp.Interfaces.ApiInterface
import com.example.ahasan.kotlinmovieapp.Interfaces.TopRatedAPI
import com.example.ahasan.kotlinmovieapp.Models.MovieList
import com.example.ahasan.kotlinmovieapp.Models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviePresenter @Inject constructor(
    private val response: ApiInterface,
    private val topRatedAPI: TopRatedAPI
) {

    lateinit var movieView: MovieView
    var retrofitList: List<Result>? = ArrayList()

    fun getRetrofitResponse() {
        response.getMovieList("11af5a2e9dda5914db6389d51f4a1e9f")
            ?.enqueue(object :
                Callback<MovieList> {

                override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {
                    try {
                        if (response?.code() == 200) {
                            retrofitList = response.body().results
                            movieView.setResult(retrofitList!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<MovieList>?, t: Throwable?) {
                    Log.e("MainActivityLog", t.toString())
                }
            }
            )
    }

    fun getRetrofitTopRelated() {
        topRatedAPI.getTopRated("11af5a2e9dda5914db6389d51f4a1e9f")
            ?.enqueue(object :
                Callback<MovieList> {

                override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {
                    try {
                        if (response?.code() == 200) {
                            retrofitList = response.body().results
                            movieView.setResult(retrofitList!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<MovieList>?, t: Throwable?) {
                    Log.e("MainActivityLog", t.toString())
                }
            }
            )
    }


}