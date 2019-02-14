package com.example.ahasan.kotlinmovieapp.Models

import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)