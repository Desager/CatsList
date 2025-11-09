package com.desager.catslist.domain.model

data class CatModel(
    override val id: String,
    val url: String,
    val breeds: List<BreedModel>,
    val isLiked: Boolean
) : Item
