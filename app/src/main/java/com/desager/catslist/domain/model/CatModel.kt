package com.desager.catslist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatModel(
    val id: String,
    val url: String,
    val breeds: List<BreedModel>,
    val isLiked: Boolean
) : Parcelable, Item
