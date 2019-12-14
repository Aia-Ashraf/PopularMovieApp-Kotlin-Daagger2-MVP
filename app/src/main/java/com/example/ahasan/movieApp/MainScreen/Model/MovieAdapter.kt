package com.example.ahasan.movieApp.MainScreen.Model

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ahasan.movieApp.R
import com.example.ahasan.movieApp.DetailsScreen.View.DetailsActivity
import com.example.ahasan.movieApp.DI.annotations.ActivityContext
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<MovieAdapter.movieViewHolder>() {
    var firstURLPart: String = "http://image.tmdb.org/t/p/w185"
    private var movieDetails = emptyList<MovieList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {

        val context = parent.context
        val layoutIdForListItem = R.layout.recyclerview_item
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return movieViewHolder(view)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(movieViewHolder: movieViewHolder, position: Int) {
        val mPosition = movieDetails.get(position)
        Picasso.get().load(firstURLPart + mPosition?.posterPath)
            .resize(700, 700)
            .placeholder(R.drawable.abc_item_background_holo_dark)
//            .centerCrop()
            .into(movieViewHolder.img)
        Log.e("MovieAdapterDebug", firstURLPart + mPosition?.posterPath)
        movieViewHolder.img.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", mPosition.id)
            startActivity(context, intent, Bundle.EMPTY)

        }
        movieViewHolder.movieTitle.setText(mPosition.title)
        movieViewHolder.movieGenres.setText(mPosition.originalLanguage)
    }

    fun setMovieList(list: List<MovieList>) {
        movieDetails = list!!
        this.notifyDataSetChanged()
    }

    inner class movieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iv_item)
        val movieTitle: TextView = itemView.findViewById(R.id.tv_movie_title)
        val movieGenres: TextView = itemView.findViewById(R.id.tv_movie_genres)
    }

    override fun getItemCount(): Int =
        movieDetails.size
}

