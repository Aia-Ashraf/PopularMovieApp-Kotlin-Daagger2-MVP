package com.example.ahasan.movieApp.DetailsScreen.Model

import com.google.gson.annotations.SerializedName


class ProductionCompany {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("logo_path")
    var logoPath: Any? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("origin_country")
    var originCountry: String? = null

}
