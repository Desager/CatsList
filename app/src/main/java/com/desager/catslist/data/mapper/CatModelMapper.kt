package com.desager.catslist.data.mapper

import com.desager.catslist.data.local.model.CatWithBreedsEntity
import com.desager.catslist.data.remote.model.CatDto
import com.desager.catslist.domain.model.CatModel

interface CatModelMapper {

    fun map(entity: CatWithBreedsEntity): CatModel

    fun map(dto: CatDto): CatModel
}

class CatModelMapperImpl(
    private val breedModelMapper: BreedModelMapper
) : CatModelMapper {

    override fun map(entity: CatWithBreedsEntity): CatModel {
        return CatModel(
            id = entity.cat.id,
            url = entity.cat.url,
            breeds = entity.breeds.map { breedModelMapper.map((it)) },
            isLiked = true
        )
    }

    override fun map(dto: CatDto): CatModel {
        return CatModel(
            id = dto.id,
            url = dto.url,
            breeds = dto.breeds.map { breedModelMapper.map(it, dto) },
            isLiked = false
        )
    }
}