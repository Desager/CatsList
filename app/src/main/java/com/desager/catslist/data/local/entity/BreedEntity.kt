package com.desager.catslist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CatEntity::class,
            parentColumns = ["id"],
            childColumns = ["cat_id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("cat_id")]
)
data class BreedEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("cat_id") val catId: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
)
