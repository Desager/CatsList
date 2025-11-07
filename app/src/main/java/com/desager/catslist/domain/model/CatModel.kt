package com.desager.catslist.domain.model

data class CatModel(
    val id: String,
    val url: String,
    val breeds: List<BreedModel>
) : Item
