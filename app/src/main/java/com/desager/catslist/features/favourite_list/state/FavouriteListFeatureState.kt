package com.desager.catslist.features.favourite_list.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureState

data class FavouriteListFeatureState(
    val cats: List<CatModel>
) : FeatureState {

    companion object {
        val default = FavouriteListFeatureState(
            cats = emptyList()
        )
    }
}
