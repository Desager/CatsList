package com.desager.catslist.domain.repository

import com.desager.catslist.domain.model.CatModel
import kotlinx.coroutines.flow.Flow

interface CatsRepository {

    suspend fun loadCats(limit: Int): List<CatModel>

    suspend fun getCats(): Flow<CatModel>

    suspend fun deleteCat(model: CatModel)

    suspend fun insertCat(model: CatModel)
}