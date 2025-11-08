package com.desager.catslist.features.cat_details.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureState

data class CatDetailsFeatureState(
    val catModel: CatModel
) : FeatureState {

    companion object {
        val default = CatDetailsFeatureState(
            catModel = CatModel(
                id = "",
                url = "",
                breeds = emptyList(),
                isLiked = false
            )
        )
    }
}
