package com.example.ahasan.kotlinmovieapp.activites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.example.ahasan.kotlinmovieapp.DetailsPresenter
import com.example.ahasan.kotlinmovieapp.DetailsView
import com.example.ahasan.kotlinmovieapp.Models.ResultX
import com.example.ahasan.kotlinmovieapp.Models.ResultXX
import com.example.ahasan.kotlinmovieapp.R
import com.example.ahasan.kotlinmovieapp.adapters.ReviewsAdapter
import com.example.ahasan.kotlinmovieapp.adapters.TrailersAdapter
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsView {

    private lateinit var mTitle: TextView
    private lateinit var mRelasedData: TextView
    private lateinit var mOverView: TextView
    private lateinit var mVote: TextView
    private lateinit var img: ImageView
    var firstURLPart: String = "http://image.tmdb.org/t/p/w185"

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTrailers: RecyclerView

    @Inject
    lateinit var presenter: DetailsPresenter

    @Inject
    lateinit var reviewsAdapter: ReviewsAdapter

    @Inject
    lateinit var trailersAdapter: TrailersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        AndroidInjection.inject(this)

        recyclerView = findViewById(R.id.rv_reviews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        presenter.detailsView = this
        recyclerView.adapter = reviewsAdapter

        recyclerViewTrailers = findViewById(R.id.rv_trailers)
        recyclerViewTrailers.layoutManager = LinearLayoutManager(this)
        recyclerViewTrailers.setHasFixedSize(true)
        presenter.detailsView = this
        recyclerViewTrailers.adapter = trailersAdapter

        mTitle = findViewById(R.id.tv_title)
        mRelasedData = findViewById(R.id.tv_releaseDate)
        mOverView = findViewById(R.id.tv_details)
        mVote = findViewById(R.id.tv_vote)
        img = findViewById(R.id.image_iv)

        val intent = intent
        val title: String = intent.getStringExtra("title")
        val releasedData: String = intent.getStringExtra("releasedData")
        val overView: String = intent.getStringExtra("overView")
        val vote: Double = intent.getDoubleExtra("vote", Double.MAX_VALUE)
        val posterPath: String = intent.getStringExtra("posterPath")
        val id: String = intent.getStringExtra("id")
        mTitle.setText(title)
        mRelasedData.text = releasedData
        mOverView.text = overView
        mVote.text = "" + vote
        presenter.getMovieReviewAPI(id)
        presenter.getMovieVideosAPI(id)

        Picasso.get().load(firstURLPart + posterPath)
            .placeholder(R.drawable.abc_item_background_holo_dark)
            .into(img)
    }

    override fun setResult(reviewsList: List<ResultX>) {
        presenter.reviewstList?.let { reviewsAdapter?.setMovieList(it) }
    }

    override fun setTrailerResult(trailersList: List<ResultXX>) {
        presenter.trailersList?.let { trailersAdapter?.setTrailersList(it) }
    }
}