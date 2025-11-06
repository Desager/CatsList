package com.desager.catslist.data.repository

import com.desager.catslist.data.local.dao.CatsDao
import com.desager.catslist.data.local.entity.CatEntity
import com.desager.catslist.data.local.model.CatWithBreeds
import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatsRepositoryImpl(
    private val api: CatsApi,
    private val dao: CatsDao
) : CatsRepository {

    override suspend fun loadCats(limit: Int): List<CatModel> {
        val result = api.getCats(10, 1)

        return result.map { it.toModel() }
    }

    override suspend fun getCats(): Flow<CatModel> {
        return dao.getCatsWithBreeds().map { it.toModel() }
    }

    override suspend fun deleteCat(model: CatModel) {
        dao.deleteCat(CatEntity.fromModel(model))
    }

    override suspend fun insertCat(model: CatModel) {
        dao.insertCatWithBreeds(CatWithBreeds.fromModel(model))
    }
}