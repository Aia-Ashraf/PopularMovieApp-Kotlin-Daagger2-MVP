package com.example.ahasan.kotlinmovieapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ahasan.kotlinmovieapp.Models.ResultX
import com.example.ahasan.kotlinmovieapp.R
import com.example.ahasan.kotlinmovieapp.annotations.ActivityContext
import javax.inject.Inject

class ReviewsAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {
    private var movieReviews = emptyList<ResultX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {

        val context = parent.context
        val layoutIdForListItem = R.layout.reviews_item
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(movieViewHolder: ReviewsViewHolder, position: Int) {
        val mPosition = movieReviews[position]

        movieViewHolder.auther.text = mPosition.author
        movieViewHolder.content.text = mPosition.content

        Log.e("p", position.toString() + "")
    }

    fun setMovieList(list: List<ResultX>) {
        movieReviews = list
        this.notifyDataSetChanged()
    }

    inner class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val auther: TextView = itemView.findViewById(R.id.tv_auther)
        val content: TextView = itemView.findViewById(R.id.tv_content)
        val reviewsTitle: TextView = itemView.findViewById(R.id.auther_title)
    }

    override fun getItemCount(): Int = movieReviews.size

}

