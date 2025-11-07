package com.desager.catslist.domain.repository

import com.desager.catslist.domain.model.CatModel
import kotlinx.coroutines.flow.Flow

interface CatsRepository {

    suspend fun loadCats(limit: Int): List<CatModel>

    suspend fun getFavouriteCats(): Flow<CatModel>

    suspend fun deleteFavouriteCat(model: CatModel)

    suspend fun insertFavouriteCat(model: CatModel)
}