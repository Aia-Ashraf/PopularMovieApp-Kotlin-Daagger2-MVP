package com.example.ahasan.kotlinmovieapp.Interfaces

import com.example.ahasan.kotlinmovieapp.Models.MovieList
import com.example.ahasan.kotlinmovieapp.Models.ReviewsModel
import com.example.ahasan.kotlinmovieapp.Models.VideosModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET("3/movie/popular")
    fun getMovieList(@Query("api_key") api_key: String): Call<MovieList>
}

interface TopRatedAPI {
    @GET("3/movie/top_rated")
    fun getTopRated(@Query("api_key") api_key: String): Call<MovieList>
}

interface MovieReviewAPI {
    @GET("3/movie/{movie_id}/reviews")
    fun getMovieReview(
        @Path("movie_id") movie_id: String, @Query("api_key") api_key: String
    ): Call<ReviewsModel>
}

interface Movietrailers {
    @GET("3/movie/{movie_id}/videos")
    fun getMovieTrailers(
        @Path("movie_id") movie_id: String, @Query("api_key") api_key: String
    ): Call<VideosModel>
}