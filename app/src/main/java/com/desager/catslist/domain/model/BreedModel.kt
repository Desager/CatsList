package com.desager.catslist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BreedModel(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
) : Parcelable, Item