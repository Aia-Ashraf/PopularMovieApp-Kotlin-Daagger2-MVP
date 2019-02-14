package com.example.ahasan.kotlinmovieapp

import com.example.ahasan.kotlinmovieapp.Models.ResultX
import com.example.ahasan.kotlinmovieapp.Models.ResultXX

interface DetailsView {

    fun setResult(reviewsList: List<ResultX>) {

    }

    fun setTrailerResult(trailersList: List<ResultXX>) {

    }
}