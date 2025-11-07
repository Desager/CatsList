package com.desager.catslist.di

import android.content.Context
import androidx.room.Room
import com.desager.catslist.data.local.CatDatabase
import com.desager.catslist.data.local.dao.CatsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideCatDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(
            context,
            CatDatabase::class.java,
            CatDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCatsDao(database: CatDatabase): CatsDao {
        return database.catsDao
    }
}