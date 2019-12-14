package com.example.ahasan.movieApp.Interfaces

import com.example.ahasan.movieApp.DetailsScreen.Model.DetailsModel
import com.example.ahasan.movieApp.MainScreen.Model.Movies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

}

interface TopRatedAPI {
    @GET("3/movie/top_rated")
    fun getTopRated(@Query("api_key") api_key: String): Observable<Movies>
}

interface MovieDetailsAPI {
    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: String, @Query("api_key") api_key: String
    ): Observable<DetailsModel>
}
