package com.desager.catslist.data.mapper

import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.remote.model.BreedDto
import com.desager.catslist.data.remote.model.CatDto
import com.desager.catslist.domain.model.BreedModel

interface BreedModelMapper {

    fun map(entity: BreedEntity): BreedModel

    fun map(breedDto: BreedDto, catDto: CatDto): BreedModel
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

    override fun map(breedDto: BreedDto, catDto: CatDto): BreedModel {
        return BreedModel(
            id = "${breedDto.id}_${catDto.id}",
            name = breedDto.name,
            temperament = breedDto.temperament,
            origin = breedDto.origin,
            description = breedDto.description
        )
    }
}