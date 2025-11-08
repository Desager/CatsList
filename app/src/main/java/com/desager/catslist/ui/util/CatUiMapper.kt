package com.desager.catslist.ui.util

import com.desager.catslist.domain.model.BreedModel
import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.ui.model.BreedUiModel
import com.desager.catslist.ui.model.CatUiModel

fun CatModel.toUiModel(): CatUiModel = CatUiModel(
    id = id,
    url = url,
    breeds = breeds.map { it.toUiModel() },
    isLiked = isLiked
)

fun BreedModel.toUiModel(): BreedUiModel = BreedUiModel(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    description = description
)

fun CatUiModel.toDomainModel(): CatModel = CatModel(
    id = id,
    url = url,
    breeds = breeds.map { it.toDomainModel() },
    isLiked = isLiked
)

fun BreedUiModel.toDomainModel(): BreedModel = BreedModel(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    description = description
)