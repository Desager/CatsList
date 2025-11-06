package com.desager.catslist.data.remote.api

import com.desager.catslist.data.remote.model.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("has_breeds") hasBreeds: Int
    ): List<CatDto>
}