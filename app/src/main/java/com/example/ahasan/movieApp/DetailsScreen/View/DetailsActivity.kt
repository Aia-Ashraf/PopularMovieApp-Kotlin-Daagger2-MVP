package com.example.ahasan.movieApp.DetailsScreen.View

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.example.ahasan.movieApp.DetailsScreen.Model.DetailsModel
import com.example.ahasan.movieApp.DetailsScreen.ViewModel.DetailsViewModel
import com.example.ahasan.movieApp.R
import com.example.ahasan.movieApp.DetailsScreen.Model.GenreAdapter
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Named

class DetailsActivity : AppCompatActivity(),
    DetailsView {

    private lateinit var mTitle: TextView
    private lateinit var mRelasedData: TextView
    private lateinit var mOriginalLang: TextView
    private lateinit var mVoteCount: TextView
    private lateinit var mVote: TextView
    private lateinit var img: ImageView
    var firstURLPart: String = "http://image.tmdb.org/t/p/w185"

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var genreAdapter: GenreAdapter
    @Inject
    @Named("detailsFactory")
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        AndroidInjection.inject(this)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        val vm = ViewModelProviders.of(this, viewModelFactory)[DetailsViewModel::class.java]

        recyclerView = findViewById(R.id.rv_genre)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = genreAdapter
        vm.detailsView = this

        mTitle = findViewById(R.id.tv_title)
        mRelasedData = findViewById(R.id.tv_releaseDate)
        mVoteCount = findViewById(R.id.tv_vote_count)
        mVote = findViewById(R.id.tv_vote)
        img = findViewById(R.id.image_iv)
        mOriginalLang = findViewById(R.id.original_language)
        val intent = intent
        val id: String = intent.getStringExtra("id")
        vm.getMovieReviewAPI(id)

    }

    override fun setResult(detailsModel: DetailsModel) {
        genreAdapter.setMovieList(detailsModel.genres)
        mTitle.setText(detailsModel.title)
        mVote.setText(detailsModel.voteAverage.toString())
        mVoteCount.setText("Vote Count :" + detailsModel.voteCount.toString())
        mRelasedData.setText("Release Date :" + detailsModel.releaseDate)
        mOriginalLang.setText("Original Language :- " + detailsModel.originalLanguage)
        Picasso.get().load(firstURLPart + detailsModel.posterPath)
            .placeholder(R.drawable.abc_item_background_holo_dark)
            .into(img)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}