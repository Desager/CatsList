package com.desager.catslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.desager.catslist.data.local.dao.CatsDao
import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.local.entity.CatEntity

@Database(
    entities = [CatEntity::class, BreedEntity::class],
    version = 1
)
abstract class CatDatabase: RoomDatabase() {

    abstract val catsDao: CatsDao

    companion object {

        const val NAME = "CAT_LIST_DATABASE"
    }
}