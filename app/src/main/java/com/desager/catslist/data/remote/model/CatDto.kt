package com.desager.catslist.data.remote.model

import com.google.gson.annotations.SerializedName

data class CatDto (
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("breeds") val breeds: ArrayList<BreedDto>,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)