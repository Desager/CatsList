package com.desager.catslist.data.repository

import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.domain.repository.CatsRepository

class CatsRepositoryImpl(
    private val api: CatsApi
) : CatsRepository {

    override suspend fun getCats(limit: Int): List<CatModel> {
        val result = api.getCats(10, 1)

        return result.map { it.toModel() }
    }
}