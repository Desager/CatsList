package com.desager.catslist.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.local.entity.CatEntity
import com.desager.catslist.domain.model.CatModel

data class CatWithBreeds(
    @Embedded val cat: CatEntity,
    @Relation(
        entity = BreedEntity::class,
        parentColumn = "id",
        entityColumn = "cat_id"
    )
    val breeds: List<BreedEntity>
) {

    fun toModel(): CatModel {
        return CatModel(
            id = cat.id,
            url = cat.url,
            breeds = breeds.map { it.toModel() }
        )
    }

    companion object {
        fun fromModel(model: CatModel): CatWithBreeds {
            return CatWithBreeds(
                cat = CatEntity.fromModel(model),
                breeds = model.breeds.map { BreedEntity.fromModel(model.id, it) }
            )
        }
    }
}
