package com.desager.catslist.domain.repository

import com.desager.catslist.domain.model.CatModel

interface CatsRepository {

    suspend fun getCats(limit: Int): List<CatModel>
}