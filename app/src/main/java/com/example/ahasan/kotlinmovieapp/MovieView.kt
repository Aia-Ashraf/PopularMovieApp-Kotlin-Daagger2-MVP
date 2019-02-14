package com.example.ahasan.kotlinmovieapp

import com.example.ahasan.kotlinmovieapp.Models.Result

interface MovieView {

    fun setResult(retrofitList: List<Result>) {
    }
}