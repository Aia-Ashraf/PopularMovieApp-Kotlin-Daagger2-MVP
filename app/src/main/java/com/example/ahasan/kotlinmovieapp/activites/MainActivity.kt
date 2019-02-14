package com.example.ahasan.kotlinmovieapp.activites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ahasan.kotlinmovieapp.Models.Result
import com.example.ahasan.kotlinmovieapp.MoviePresenter
import com.example.ahasan.kotlinmovieapp.MovieView
import com.example.ahasan.kotlinmovieapp.R
import com.example.ahasan.kotlinmovieapp.adapters.MovieAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MovieView {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var movieAdapter: MovieAdapter
    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var presenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        presenter.movieView = this
        recyclerView.adapter = movieAdapter
        presenter.getRetrofitResponse()
    }
    override fun setResult(retrofitList: List<Result>) {
        presenter.retrofitList?.let { movieAdapter?.setMovieList(it) }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.item_top_related -> {
                presenter.getRetrofitTopRelated()
                true
            }
            R.id.item_popular -> {
                presenter.getRetrofitResponse()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
