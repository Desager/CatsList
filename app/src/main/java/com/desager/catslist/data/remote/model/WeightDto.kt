package com.desager.catslist.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeightDto (
    @SerializedName("imperial") val imperial: String,
    @SerializedName("metric") val metric: String
)