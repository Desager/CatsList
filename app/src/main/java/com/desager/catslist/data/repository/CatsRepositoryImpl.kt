package com.desager.catslist.data.repository

import com.desager.catslist.data.local.dao.CatsDao
import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.local.entity.CatEntity
import com.desager.catslist.data.local.model.CatWithBreedsEntity
import com.desager.catslist.data.mapper.CatModelMapper
import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatsRepositoryImpl(
    private val api: CatsApi,
    private val dao: CatsDao,
    private val catModelMapper: CatModelMapper
) : CatsRepository {

    override suspend fun loadCats(limit: Int): List<CatModel> {
        val result = api.getCats(10, 1)

        return result.map(catModelMapper::map)
    }

    override suspend fun getFavouriteCats(): Flow<CatModel> {
        return dao.getCatsWithBreeds().map(catModelMapper::map)
    }

    override suspend fun deleteFavouriteCat(model: CatModel) {
        val catEntity = CatEntity(
            id = model.id,
            url = model.url
        )

        dao.deleteCat(catEntity)
    }

    override suspend fun insertFavouriteCat(model: CatModel) {
        val catEntity = CatEntity(
            id = model.id,
            url = model.url
        )

        val breedsEntity = model.breeds.map {
            BreedEntity(
                id = it.id,
                catId = model.id,
                name = it.name,
                temperament = it.temperament,
                origin = it.origin,
                description = it.description
            )
        }

        val catWithBreedsEntity = CatWithBreedsEntity(catEntity, breedsEntity)

        dao.insertCatWithBreeds(catWithBreedsEntity)
    }
}