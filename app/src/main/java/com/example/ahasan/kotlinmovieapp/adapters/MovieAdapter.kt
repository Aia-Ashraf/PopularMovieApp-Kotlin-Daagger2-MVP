package com.example.ahasan.kotlinmovieapp.adapters

import android.annotation.SuppressLint
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
import com.example.ahasan.kotlinmovieapp.Models.Result
import com.example.ahasan.kotlinmovieapp.R
import com.example.ahasan.kotlinmovieapp.activites.DetailsActivity
import com.example.ahasan.kotlinmovieapp.annotations.ActivityContext
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<MovieAdapter.movieViewHolder>() {
    var firstURLPart: String = "http://image.tmdb.org/t/p/w185"
    private var movieDetails = emptyList<Result>()

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
        Log.e("MovieAdapterDebug", firstURLPart + mPosition?.posterPath)

        Picasso.get().load(firstURLPart + mPosition?.posterPath)
//            .resize(700, 1750)
            .placeholder(R.drawable.abc_item_background_holo_dark)
//            .centerCrop()
            .into(movieViewHolder.img)
        Log.e("MovieAdapterDebug", firstURLPart + mPosition?.posterPath)
        movieViewHolder.img.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title", mPosition.title)
            intent.putExtra("releasedData", mPosition.releaseDate)
            intent.putExtra("overView", mPosition.overview)
            intent.putExtra("vote", mPosition.voteAverage)
            intent.putExtra("posterPath", mPosition.posterPath)
            intent.putExtra("id",mPosition.id)
            startActivity(context, intent, Bundle.EMPTY)
            Log.e("MovieID", mPosition.id)
        }
        Log.e("p", position.toString() + "")

    }

    fun setMovieList(list: List<Result>) {
        movieDetails = list
        this.notifyDataSetChanged()
    }

    inner class movieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iv_item)
    }

    override fun getItemCount(): Int =
        movieDetails.size
}

