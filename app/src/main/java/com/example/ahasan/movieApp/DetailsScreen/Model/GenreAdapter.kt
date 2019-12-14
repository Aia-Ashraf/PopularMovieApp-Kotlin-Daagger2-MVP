package com.example.ahasan.movieApp.DetailsScreen.Model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ahasan.movieApp.R
import com.example.ahasan.movieApp.DI.annotations.ActivityContext
import javax.inject.Inject

class GenreAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<GenreAdapter.ReviewsViewHolder>() {
    private var movieReviews = emptyList<GenreModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {

        val context = parent.context
        val layoutIdForListItem = R.layout.genres_item
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(movieViewHolder: ReviewsViewHolder, position: Int) {
        val mPosition = movieReviews[position]

        movieViewHolder.auther.text = mPosition.mName

    }

    fun setMovieList(list: List<GenreModel>?) {
        movieReviews = list!!
        this.notifyDataSetChanged()
    }

    inner class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val auther: TextView = itemView.findViewById(R.id.tv_auther)
    }

    override fun getItemCount(): Int = movieReviews.size
}

