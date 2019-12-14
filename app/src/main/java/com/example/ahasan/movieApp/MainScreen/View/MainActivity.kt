package com.example.ahasan.movieApp.MainScreen.View

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.ahasan.movieApp.MainScreen.ViewModel.MovieViewModel
import com.example.ahasan.movieApp.R
import com.example.ahasan.movieApp.MainScreen.Model.MovieAdapter
import com.example.ahasan.movieApp.MainScreen.Model.MovieList
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MovieView {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var movieAdapter: MovieAdapter
    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var viewModel: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        val vm = ViewModelProviders.of(this, viewModel)[MovieViewModel::class.java]
        vm.getRetrofitTopRelated()
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        vm.movieView = this
        recyclerView.adapter = movieAdapter


    }

    override fun setResult(retrofitList: List<MovieList>) {
        movieAdapter.setMovieList(retrofitList)

    }
}
