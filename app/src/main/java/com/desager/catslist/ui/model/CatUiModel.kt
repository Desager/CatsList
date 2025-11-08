package com.desager.catslist.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatUiModel(
    val id: String,
    val url: String,
    val breeds: List<BreedUiModel>,
    val isLiked: Boolean
) : Parcelable