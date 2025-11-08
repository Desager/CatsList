package com.desager.catslist.features.cat_details.event

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureEvent

sealed interface CatDetailsFeatureEvent : FeatureEvent {

    class SetIsLiked(val model: CatModel) : CatDetailsFeatureEvent

    object LikedSet : CatDetailsFeatureEvent
}