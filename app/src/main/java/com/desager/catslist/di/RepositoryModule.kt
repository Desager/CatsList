package com.desager.catslist.di

import com.desager.catslist.data.local.dao.CatsDao
import com.desager.catslist.data.mapper.BreedModelMapper
import com.desager.catslist.data.mapper.BreedModelMapperImpl
import com.desager.catslist.data.mapper.CatModelMapper
import com.desager.catslist.data.mapper.CatModelMapperImpl
import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.data.repository.CatsRepositoryImpl
import com.desager.catslist.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBreedModelMapper(): BreedModelMapper {
        return BreedModelMapperImpl()
    }

    @Provides
    @Singleton
    fun provideCatModelMapper(breedModelMapper: BreedModelMapper): CatModelMapper {
        return CatModelMapperImpl(breedModelMapper)
    }

    @Provides
    @Singleton
    fun provideRepository(
        catsApi: CatsApi,
        catsDao: CatsDao,
        catModelMapper: CatModelMapper
    ): CatsRepository {
        return CatsRepositoryImpl(catsApi, catsDao, catModelMapper)
    }
}