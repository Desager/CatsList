package com.desager.catslist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.desager.catslist.domain.model.CatModel

@Entity
data class CatEntity(
    @PrimaryKey val id: String,
    val url: String
) {

    companion object {
        fun fromModel(model: CatModel): CatEntity {
            return CatEntity(
                id = model.id,
                url = model.url
            )
        }
    }
}
