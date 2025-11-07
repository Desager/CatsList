package com.desager.catslist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey val id: String,
    val url: String
)
