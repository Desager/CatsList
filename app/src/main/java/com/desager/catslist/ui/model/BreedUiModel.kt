package com.desager.catslist.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BreedUiModel(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
) : Parcelable