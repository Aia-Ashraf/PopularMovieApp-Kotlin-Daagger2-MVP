package com.example.ahasan.kotlinmovieapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ahasan.kotlinmovieapp.Models.ResultXX
import com.example.ahasan.kotlinmovieapp.R
import com.example.ahasan.kotlinmovieapp.annotations.ActivityContext
import javax.inject.Inject
import android.content.Intent
import android.net.Uri


class TrailersAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {
    private var movieTrailers = emptyList<ResultXX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {

        val context = parent.context
        val layoutIdForListItem = R.layout.trailers_item
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return TrailersViewHolder(view)
    }

    override fun onBindViewHolder(trailersViewHolder: TrailersViewHolder, position: Int) {
        val mPosition = movieTrailers[position]

        trailersViewHolder.trailerTitle.text = mPosition.name
      trailersViewHolder.img.setOnClickListener{
          val webIntent = Intent(
              Intent.ACTION_VIEW,
              Uri.parse("http://m.youtube.com/watch?v="+mPosition.id)
          )
          context.startActivity(webIntent)
      }

        Log.e("p", position.toString() + "naaaaaaaaaaaaaaame" +mPosition.name)
    }

    fun setTrailersList(list: List<ResultXX>) {
        movieTrailers = list
        this.notifyDataSetChanged()
    }

    inner class TrailersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iv_trailers)
        val trailerTitle: TextView = itemView.findViewById(R.id.tv_trailer)

    }

    override fun getItemCount(): Int =
        movieTrailers.size

}