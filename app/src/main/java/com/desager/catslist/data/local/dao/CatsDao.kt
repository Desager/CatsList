package com.desager.catslist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.local.entity.CatEntity
import com.desager.catslist.data.local.model.CatWithBreedsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsDao {

    @Transaction
    @Query("SELECT * FROM catentity")
    fun getCatsWithBreeds(): Flow<CatWithBreedsEntity>

    @Delete
    suspend fun deleteCat(catEntity: CatEntity)

    @Upsert
    suspend fun insertCat(catEntity: CatEntity)

    @Upsert
    suspend fun insertBreeds(breeds: List<BreedEntity>)

    @Transaction
    suspend fun insertCatWithBreeds(catWithBreeds: CatWithBreedsEntity) {
        insertCat(catWithBreeds.cat)
        insertBreeds(catWithBreeds.breeds)
    }
}