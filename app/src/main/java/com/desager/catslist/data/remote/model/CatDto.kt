package com.desager.catslist.data.remote.model

import com.desager.catslist.domain.model.CatModel
import com.google.gson.annotations.SerializedName

data class CatDto (
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("breeds") val breeds: ArrayList<BreedsDto>,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
) {

    fun toModel(): CatModel {
        return CatModel(
            url = url,
            breeds = breeds.map { it.toModel() }
        )
    }
}