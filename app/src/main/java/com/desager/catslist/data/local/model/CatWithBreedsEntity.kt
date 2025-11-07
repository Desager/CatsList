package com.desager.catslist.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.desager.catslist.data.local.entity.BreedEntity
import com.desager.catslist.data.local.entity.CatEntity

data class CatWithBreedsEntity(
    @Embedded val cat: CatEntity,
    @Relation(
        entity = BreedEntity::class,
        parentColumn = "id",
        entityColumn = "cat_id"
    )
    val breeds: List<BreedEntity>
)
