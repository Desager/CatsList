package com.desager.catslist.data.mapper

import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.remote.model.BreedDto
import com.desager.catslist.domain.model.BreedModel

interface BreedModelMapper {

    fun map(entity: BreedEntity): BreedModel

    fun map(dto: BreedDto): BreedModel
}

class BreedModelMapperImpl : BreedModelMapper {

    override fun map(entity: BreedEntity): BreedModel {
        return BreedModel(
            id = entity.id,
            name = entity.name,
            temperament = entity.temperament,
            origin = entity.origin,
            description = entity.description
        )
    }

    override fun map(dto: BreedDto): BreedModel {
        return BreedModel(
            id = dto.id,
            name = dto.name,
            temperament = dto.temperament,
            origin = dto.origin,
            description = dto.description
        )
    }
}