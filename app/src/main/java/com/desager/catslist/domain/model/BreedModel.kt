package com.desager.catslist.domain.model

data class BreedModel(
    override val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
) : Item